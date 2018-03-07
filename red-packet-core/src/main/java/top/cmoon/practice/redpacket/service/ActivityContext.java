package top.cmoon.practice.redpacket.service;

import top.cmoon.practice.activity.domain.RedPacketPool;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class ActivityContext {


    private ActivityId activityId;


    public RedPacketPool redPacketPool() {
        return null;
    }

    public ActivityId getActivityId() {
        return activityId;
    }

    public void setActivityId(ActivityId activityId) {
        this.activityId = activityId;
    }

    public ActivityConfig getActivityConfig() {
        return null;
    }
}
