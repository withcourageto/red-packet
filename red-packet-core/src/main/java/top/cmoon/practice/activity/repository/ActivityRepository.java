package top.cmoon.practice.activity.repository;

import top.cmoon.practice.activity.domain.Activity;

/**
 * Created by cool_moon on 2018/3/2.
 */
public interface ActivityRepository {


    Activity findById(int activityId);

    void decrementRemainingRedPackedNum(int activityId);
}
