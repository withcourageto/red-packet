package top.cmoon.practice.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import top.cmoon.practice.test.po.Activity;
import top.cmoon.practice.test.po.RedPacket;
import top.cmoon.practice.test.po.RedPacketPool;
import top.cmoon.practice.test.vo.TakeResponse;
import top.cmoon.practice.test.event.WinRedPacketEvent;
import top.cmoon.practice.test.repo.RedPacketPoolRepo;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Service
class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private CounterService counterService;
    private ConcurrentSkipListSet<Integer> queue = new ConcurrentSkipListSet<>();

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private RedPacketPoolRepo redPacketPoolRepo;


    @Override
    public TakeResponse takeRedPacket(Activity activity, Integer userId) {


        boolean success = queue.add(userId);

        if (!success) { // 正在抢红包
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "正在抢红包，稍后重试";
            return takeResponse;
        }

        boolean reachLimit = counterService.reachLimit(activity, userId, activity.getUserMaxNum());
        if (reachLimit) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "您已经到达活动红包限制";
            return takeResponse;
        }


        RedPacketPool redPacketPool = choosePool(activity);
        RedPacket redPacket = redPacketPool.randomIssueRedPacket();

        if (redPacket == null) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "再接再厉";
            return takeResponse;
        }

        WinRedPacketEvent winRedPacketEvent = new WinRedPacketEvent(null, redPacket, activity.getId(), userId);
        applicationContext.publishEvent(winRedPacketEvent);

        TakeResponse takeResponse = new TakeResponse();
        takeResponse.code = 0;
        takeResponse.msg = "恭喜获得红包";
        takeResponse.amount = redPacket.getMoney().doubleValue();

        return takeResponse;
    }

    @Override
    public void decrementRedPacketNum(int redPacketId, int redPacketPoolId) {
        redPacketPoolRepo.decrementRedPacketNum(redPacketPoolId);
    }

    private RedPacketPool choosePool(Activity activity) {

        RedPacketPool redPacketPool = redPacketPoolRepo.findFirstByActivityId(activity.getId());
        return redPacketPool;
    }
}
