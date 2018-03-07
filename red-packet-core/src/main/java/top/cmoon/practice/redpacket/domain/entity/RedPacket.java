package top.cmoon.practice.redpacket.domain.entity;

import java.math.BigDecimal;

/**
 * Created by cool_moon on 2018/2/28.
 */
public class RedPacket {

    private int id;
    private int activityId;
    private int poolId;

    private BigDecimal money;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
