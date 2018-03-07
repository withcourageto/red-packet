package top.cmoon.practice.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.cmoon.practice.test.po.Activity;
import top.cmoon.practice.test.po.RedPacket;
import top.cmoon.practice.test.po.RedPacketPool;
import top.cmoon.practice.test.repo.RedPacketPoolRepo;
import top.cmoon.practice.test.repo.RedPacketRpo;
import top.cmoon.practice.test.vo.TakeResponse;
import top.cmoon.practice.test.repo.ActivityRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    RedPacketPoolRepo redPacketPoolRepo;


    @Autowired
    RedPacketRpo redPacketRpo;


    @Autowired
    RedPacketService redPacketService;

    @Autowired
    CounterService counterService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public TakeResponse takeRedPacket(int activityId, Integer userId) {


        Activity activity = activityRepo.findOne(activityId);

        counterService.incrementTryCount(activityId);

        if (!activity.isStart()) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "活动未开始";

            return takeResponse;
        }

        if (!activity.isEnd()) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "活动已经结束";

            return takeResponse;
        }


        if (!activity.noRedPacket()) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = "红包已经被抢光";

            return takeResponse;
        }


        int limit = activity.getUserMaxNum();
        boolean reachLimit = counterService.reachLimit(activity, userId, limit);
        if (reachLimit) {
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.code = 1;
            takeResponse.msg = new String("您已经到达活动红包限制");

            return takeResponse;
        }

        TakeResponse takeResponse = redPacketService.takeRedPacket(activity, userId);

        return takeResponse;
    }


    @Override
    public void decrementRedPacketNum(int activityId) {

    }

    @Override
    public void addAndInitActivity(Activity activity) {

        Activity dbActivity = activityRepo.save(activity);
        initRedPacketAndPool(dbActivity);
    }

    Random random = new Random();

    private void initRedPacketAndPool(Activity activity) {

        int nums = activity.getRedPacketNums();

        BigDecimal maxAmount = activity.getMaxAmount();
        BigDecimal minAmount = activity.getMinAmount();

        int range = maxAmount.intValue() - minAmount.intValue();


        List<RedPacket> redPacketList = new ArrayList<>();

        for (int i = 0; i < nums; i++) {

            int randomVal = random.nextInt(range);
            double redPacketAmount = minAmount.doubleValue() + randomVal;

            RedPacket redPacket = new RedPacket();

            redPacket.setActivityId(activity.getId());
            redPacket.setMoney(new BigDecimal(redPacketAmount));
            redPacketList.add(redPacket);
        }
        redPacketList.stream().forEach(r -> redPacketRpo.save(r));


        RedPacketPool pool = new RedPacketPool();

        pool.setRedPacketNums(nums);
        pool.setActivityId(activity.getId());
        pool.setRedPacketRemainNums(nums);

        redPacketPoolRepo.save(pool);

        List<Integer> redPacketIds = new ArrayList<>();
        redPacketList.stream().forEach(r -> redPacketIds.add(r.getId()));

        redisTemplate.opsForList().leftPushAll("red_packet_pool:" + pool.getId(), redPacketIds);
    }
}
