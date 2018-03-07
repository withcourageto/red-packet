package top.cmoon.practice.activity.repository.impl;

import org.springframework.stereotype.Repository;
import top.cmoon.practice.activity.domain.RedPacketPool;
import top.cmoon.practice.activity.repository.RedPacketPoolRepository;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Repository
class RedPacketPoolRepositoryImpl implements RedPacketPoolRepository {


    @Override
    public RedPacketPool findByActivityId(int activityId) {
        return null;
    }
}
