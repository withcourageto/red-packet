package top.cmoon.practice.activity.event;

import org.springframework.context.ApplicationEvent;
import top.cmoon.practice.redpacket.service.ActivityId;
import top.cmoon.practice.redpacket.service.RedPacketId;

import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/3/2.
 */
public class WinningRedPacketEvent extends ApplicationEvent {
    public WinningRedPacketEvent(Object source) {
        super(source);
    }

    private ActivityId activityId;

    private RedPacketId redPacketId;

    private int userId;

    private LocalDateTime winningTime;


    public ActivityId getActivityId() {
        return activityId;
    }

    public void setActivityId(ActivityId activityId) {
        this.activityId = activityId;
    }

    public RedPacketId getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(RedPacketId redPacketId) {
        this.redPacketId = redPacketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(LocalDateTime winningTime) {
        this.winningTime = winningTime;
    }
}
