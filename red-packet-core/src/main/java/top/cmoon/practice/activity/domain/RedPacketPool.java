package top.cmoon.practice.activity.domain;

import org.springframework.data.redis.core.RedisTemplate;
import top.cmoon.practice.infr.ContextTool;
import top.cmoon.practice.redpacket.service.ActivityId;
import top.cmoon.practice.redpacket.service.RedPacketId;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class RedPacketPool implements Serializable {


    private ActivityId activityId;


    public RedPacketPool(ActivityId activityId) {
        this.activityId = activityId;
    }


    public RedPacketId takeAwayRedPacket() {
        RedisTemplate redisTemplate = ContextTool.getBean(RedisTemplate.class);

        Integer redPacketId = (Integer) redisTemplate.opsForList().rightPop("red_packets:" + activityId.getValue());
        return new RedPacketId(redPacketId);
    }


    public RedPacketId randomTakeRedPacket() {
        return null;
    }
}
