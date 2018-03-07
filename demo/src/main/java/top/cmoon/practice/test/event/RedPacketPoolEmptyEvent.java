package top.cmoon.practice.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class RedPacketPoolEmptyEvent extends ApplicationEvent {


    private int activityId;
    private int redPacketPoolId;


    public RedPacketPoolEmptyEvent(Object source, int activityId, int redPacketPoolId) {
        super(source);
        this.activityId = activityId;
        this.redPacketPoolId = redPacketPoolId;
    }

    public int getActivityId() {
        return activityId;
    }

    public int getRedPacketPoolId() {
        return redPacketPoolId;
    }
}
