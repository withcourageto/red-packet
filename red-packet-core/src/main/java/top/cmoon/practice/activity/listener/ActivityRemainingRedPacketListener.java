package top.cmoon.practice.activity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.cmoon.practice.activity.event.WinningRedPacketEvent;
import top.cmoon.practice.activity.service.ActivityService;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Component
public class ActivityRemainingRedPacketListener implements ApplicationListener<WinningRedPacketEvent> {


    @Autowired
    private ActivityService activityService;


    @Override
    public void onApplicationEvent(WinningRedPacketEvent winningRedPacketEvent) {

        activityService.decrementRemainingRedPackedNum(winningRedPacketEvent.getActivityId().getValue());
    }
}
