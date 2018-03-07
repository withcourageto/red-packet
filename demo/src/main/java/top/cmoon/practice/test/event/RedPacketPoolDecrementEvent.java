package top.cmoon.practice.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class RedPacketPoolDecrementEvent extends ApplicationEvent {


    private int activityId;
    private int redPacketPoolId;
    private int redPacketId;

    public RedPacketPoolDecrementEvent(Object source, int activityId, int redPacketPoolId, int redPacketId) {
        super(source);
        this.activityId = activityId;
        this.redPacketPoolId = redPacketPoolId;
        this.redPacketId = redPacketId;
    }


    public int getActivityId() {
        return activityId;
    }

    public int getRedPacketPoolId() {
        return redPacketPoolId;
    }

    public int getRedPacketId() {
        return redPacketId;
    }
}
