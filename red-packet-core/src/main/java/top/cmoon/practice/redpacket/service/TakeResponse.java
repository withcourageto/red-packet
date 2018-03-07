package top.cmoon.practice.redpacket.service;

import java.math.BigDecimal;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class TakeResponse {

    private boolean winning;    // 是否抽中红包
    private ActivityId activityId;
    private BigDecimal money;
    private RedPacketId redPacketId;

    private String msg;

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
