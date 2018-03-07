package top.cmoon.practice.test.event;

import org.springframework.context.ApplicationEvent;
import top.cmoon.practice.test.po.RedPacket;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class WinRedPacketEvent extends ApplicationEvent {


    private RedPacket redPacket;
    private int userId;
    private int activityId;

    public WinRedPacketEvent(Object source, RedPacket redPacket, int activityId, int userId) {
        super(source);

        this.redPacket = redPacket;
        this.userId = userId;
        this.activityId = activityId;
    }

    public WinRedPacketEvent(Object source) {
        super(source);
    }

    public RedPacket getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
