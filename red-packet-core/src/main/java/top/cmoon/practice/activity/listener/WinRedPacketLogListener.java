package top.cmoon.practice.activity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.cmoon.practice.activity.event.WinningRedPacketEvent;
import top.cmoon.practice.activity.po.WinRedPacketLogPO;
import top.cmoon.practice.activity.service.ActivityService;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Component
public class WinRedPacketLogListener implements ApplicationListener<WinningRedPacketEvent> {

    @Autowired
    private ActivityService activityService;


    @Override
    public void onApplicationEvent(WinningRedPacketEvent winningRedPacketEvent) {
        WinRedPacketLogPO winRedPacketLog = new WinRedPacketLogPO();

        winRedPacketLog.setActivityId(winningRedPacketEvent.getActivityId().getValue());
        winRedPacketLog.setRedPacketId(winningRedPacketEvent.getRedPacketId().getValue());
        winRedPacketLog.setWinningTime(winningRedPacketEvent.getWinningTime());
        winRedPacketLog.setUserId(winningRedPacketEvent.getUserId());

        activityService.saveWinningLog(winRedPacketLog);
    }
}
