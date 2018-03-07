package top.cmoon.practice.activity.domain;

import top.cmoon.practice.activity.service.RuleResponse;
import top.cmoon.practice.redpacket.service.ActivityId;
import top.cmoon.practice.redpacket.service.RedPacketId;
import top.cmoon.practice.redpacket.service.TakeResponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by cool_moon on 2018/3/2.
 */
public class Activity implements Serializable {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private ActivityId activityId;
    private int redPacketRemainingNum;
    private RedPacketPool redPacketPool;


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ActivityId getActivityId() {
        return activityId;
    }

    public void setActivityId(ActivityId activityId) {
        this.activityId = activityId;
    }


    public void setRedPacketRemainingNum(int redPacketRemainingNum) {
        this.redPacketRemainingNum = redPacketRemainingNum;
    }


    public void setRedPacketPool(RedPacketPool redPacketPool) {
        this.redPacketPool = redPacketPool;
    }

    public RuleResponse checkRule(int userId) {


        if (LocalDateTime.now().isBefore(startTime)) {
            return new RuleResponse(false, "活动未开始");
        }

        if (LocalDateTime.now().isAfter(endTime)) {
            return new RuleResponse(false, "活动已经结束");
        }


        if (redPacketRemainingNum <= 0) {
            return new RuleResponse(false, "红包已经抢完");
        }


        return new RuleResponse(true, null);
    }


    private Random random = new Random();

    public TakeResponse takeRedPacket(int userId) {

        int randomNum = random.nextInt(10000);
        if (randomNum % 3 != 0 && randomNum % 7 != 0) { // 无法抽取红包
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.setWinning(false);
            takeResponse.setMsg("稍后重试");
            return takeResponse;
        }


        RedPacketId redPacketId = redPacketPool.takeAwayRedPacket();
        if (redPacketId == null) { // 红包被抢完
            TakeResponse takeResponse = new TakeResponse();
            takeResponse.setWinning(false);
            takeResponse.setMsg("红包已被抢光");


            return takeResponse;
        }


        // 用户获得红包
        TakeResponse takeResponse = new TakeResponse();
        takeResponse.setWinning(true);
        takeResponse.setRedPacketId(redPacketId);
        takeResponse.setActivityId(activityId);
        takeResponse.setMsg("稍后重试");

        return takeResponse;
    }


}
