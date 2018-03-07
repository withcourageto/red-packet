package top.cmoon.practice.activity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.activity.dao.ActivityDao;
import top.cmoon.practice.activity.domain.Activity;
import top.cmoon.practice.activity.domain.RedPacketPool;
import top.cmoon.practice.activity.po.ActivityPO;
import top.cmoon.practice.activity.repository.ActivityRepository;
import top.cmoon.practice.redpacket.service.ActivityId;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Repository
class ActivityRepositoryImpl implements ActivityRepository {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public Activity findById(int activityId) {


        ActivityPO activityPO = activityDao.getOne(activityId);

        Activity activity = new Activity();

        ActivityId activityIdO = new ActivityId(activityPO.getId());

        activity.setStartTime(activityPO.getStartTime());
        activity.setEndTime(activityPO.getEndTime());
        activity.setActivityId(activityIdO);
        activity.setRedPacketRemainingNum(activityPO.getRedPacketRemainingNum());

        activity.setRedPacketPool(new RedPacketPool(activityIdO));

        return activity;
    }

    /**
     * TODO 线程不安全
     *
     * @param activityId
     */
    @Override
    public void decrementRemainingRedPackedNum(int activityId) {

        activityDao.decrementRemainNum(activityId);
    }
}
