package top.cmoon.practice.activity.po;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Entity
@Table(name = "r_win_red_packet_log")
public class WinRedPacketLogPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private Integer activityId;
    private Integer userId;
    private Integer redPacketId;
    private LocalDateTime winningTime;


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Integer redPacketId) {
        this.redPacketId = redPacketId;
    }

    public LocalDateTime getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(LocalDateTime winningTime) {
        this.winningTime = winningTime;
    }
}
