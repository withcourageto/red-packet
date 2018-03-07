package top.cmoon.practice.test.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/2/28.
 */

@Entity
@Table(name = "r_red_packet")
public class RedPacket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal money;

    @Column(name = "activity_id")
    private Integer activityId;


    private String type;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @Column(name = "best_wishes")
    private String bestWishes;

    private String scenarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public String getBestWishes() {
        return bestWishes;
    }

    public void setBestWishes(String bestWishes) {
        this.bestWishes = bestWishes;
    }

    public String getScenarios() {
        return scenarios;
    }

    public void setScenarios(String scenarios) {
        this.scenarios = scenarios;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
