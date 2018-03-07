package top.cmoon.practice.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import top.cmoon.practice.activity.dao.WinLogDao;
import top.cmoon.practice.activity.domain.Activity;
import top.cmoon.practice.activity.event.WinningRedPacketEvent;
import top.cmoon.practice.activity.po.WinRedPacketLogPO;
import top.cmoon.practice.activity.repository.ActivityRepository;
import top.cmoon.practice.redpacket.service.TakeResponse;

import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Service
class ActivityServiceImpl implements ActivityService {


    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WinLogDao winLogDao;


    @Override
    public TakeResponse takeRedPacket(int activityId, int userId) {
        Activity activity = activityRepository.findById(activityId);

        RuleResponse ruleResponse = activity.checkRule(userId);
        if (!ruleResponse.isOk()) {
            return buildResponse(ruleResponse);
        }
        TakeResponse takeResponse = activity.takeRedPacket(userId);

        if (takeResponse.isWinning()) {
            WinningRedPacketEvent winningRedPacketEvent = wrapperWinningEvent(takeResponse, userId);
            applicationContext.publishEvent(winningRedPacketEvent);
        }

        return takeResponse;
    }

    @Override
    public void saveWinningLog(WinRedPacketLogPO winRedPacketLog) {

        winLogDao.save(winRedPacketLog);

    }

    @Override
    public void decrementRemainingRedPackedNum(int activityId) {

        activityRepository.decrementRemainingRedPackedNum(activityId);
    }


    private WinningRedPacketEvent wrapperWinningEvent(TakeResponse takeResponse, int userId) {

        WinningRedPacketEvent event = new WinningRedPacketEvent(null);

        event.setActivityId(takeResponse.getActivityId());
        event.setRedPacketId(takeResponse.getRedPacketId());
        event.setUserId(userId);
        event.setWinningTime(LocalDateTime.now());

        return event;
    }


    private TakeResponse buildResponse(RuleResponse ruleResponse) {

        TakeResponse takeResponse = new TakeResponse();

        takeResponse.setWinning(false);
        takeResponse.setMsg(ruleResponse.getMsg());
        return takeResponse;
    }


}
