package top.cmoon.practice.test.service;

import top.cmoon.practice.test.po.Activity;
import top.cmoon.practice.test.vo.TakeResponse;

/**
 * Created by cool_moon on 2018/2/28.
 */
public interface ActivityService {

    TakeResponse takeRedPacket(int activityId, Integer userId);

    void decrementRedPacketNum(int activityId);

    void addAndInitActivity(Activity activity);
}
