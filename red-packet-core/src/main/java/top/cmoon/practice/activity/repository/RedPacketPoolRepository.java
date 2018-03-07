package top.cmoon.practice.activity.repository;

import top.cmoon.practice.activity.domain.RedPacketPool;

/**
 * Created by cool_moon on 2018/3/2.
 */
public interface RedPacketPoolRepository {


    RedPacketPool findByActivityId(int activityId);
}
