package top.cmoon.practice.test.po;

import org.springframework.data.redis.core.RedisTemplate;
import top.cmoon.practice.test.event.RedPacketPoolDecrementEvent;
import top.cmoon.practice.test.event.RedPacketPoolEmptyEvent;
import top.cmoon.practice.test.repo.RedPacketRpo;
import top.cmoon.practice.test.service.ContextGetter;

import javax.persistence.*;
import java.util.Random;

/**
 * Created by cool_moon on 2018/3/3.
 */
@Entity
@Table(name = "r_red_packet_pool")
public class RedPacketPool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "activity_id")
    private Integer activityId;

    @Column(name = "red_packet_nums")
    private Integer redPacketNums;

    @Column(name = "red_packet_remain_nums")
    private Integer redPacketRemainNums;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getRedPacketNums() {
        return redPacketNums;
    }

    public void setRedPacketNums(Integer redPacketNums) {
        this.redPacketNums = redPacketNums;
    }

    public Integer getRedPacketRemainNums() {
        return redPacketRemainNums;
    }

    public void setRedPacketRemainNums(Integer redPacketRemainNums) {
        this.redPacketRemainNums = redPacketRemainNums;
    }

    @Transient
    private Random random = new Random();

    @Transient
    public RedPacket randomIssueRedPacket() {


        if (redPacketRemainNums <= 0) {
            return null;
        }

        int randomNum = random.nextInt(10000);
        if (randomNum % 3 != 0 && randomNum % 7 != 0) {
            return null;
        }

        RedisTemplate redisTemplate = ContextGetter.getBean(RedisTemplate.class);
        Integer redPacketId = (Integer) redisTemplate.opsForList().rightPop("red_packet_pool:" + id);

        if (redPacketId == null) {

            // 发布红包池抢空事件
            RedPacketPoolEmptyEvent redPacketPoolEmptyEvent = new RedPacketPoolEmptyEvent(null, activityId, this.id);
            ContextGetter.getApplicationContext().publishEvent(redPacketPoolEmptyEvent);

            return null;
        }

        // 发布红包减少事件
        RedPacketPoolDecrementEvent redPacketPoolDecrementEvent = new RedPacketPoolDecrementEvent(null, activityId, this.id, redPacketId);
        ContextGetter.getApplicationContext().publishEvent(redPacketPoolDecrementEvent);


        RedPacketRpo redPacketRpo = ContextGetter.getBean(RedPacketRpo.class);
        RedPacket redPacket = redPacketRpo.findOne(Integer.valueOf(redPacketId));

        return redPacket;
    }
}
