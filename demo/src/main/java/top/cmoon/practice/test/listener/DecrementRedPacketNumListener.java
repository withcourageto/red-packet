package top.cmoon.practice.test.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.cmoon.practice.test.event.RedPacketPoolDecrementEvent;
import top.cmoon.practice.test.service.ActivityService;
import top.cmoon.practice.test.service.RedPacketService;

/**
 * Created by cool_moon on 2018/3/3.
 */

@Component
public class DecrementRedPacketNumListener implements ApplicationListener<RedPacketPoolDecrementEvent> {


    @Autowired
    private ActivityService activityService;

    @Autowired
    private RedPacketService redPacketService;


    @Override
    public void onApplicationEvent(RedPacketPoolDecrementEvent redPacketPoolDecrementEvent) {
        activityService.decrementRedPacketNum(redPacketPoolDecrementEvent.getActivityId());
        redPacketService.decrementRedPacketNum(redPacketPoolDecrementEvent.getRedPacketId(), redPacketPoolDecrementEvent.getRedPacketPoolId());
    }
}
