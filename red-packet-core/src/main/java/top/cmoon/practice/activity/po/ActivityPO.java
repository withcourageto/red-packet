package top.cmoon.practice.activity.po;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Entity
@Table(name = "r_activity")
public class ActivityPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer redPacketNum;
    private Integer redPacketRemainingNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getRedPacketNum() {
        return redPacketNum;
    }

    public void setRedPacketNum(Integer redPacketNum) {
        this.redPacketNum = redPacketNum;
    }

    public Integer getRedPacketRemainingNum() {
        return redPacketRemainingNum;
    }

    public void setRedPacketRemainingNum(Integer redPacketRemainingNum) {
        this.redPacketRemainingNum = redPacketRemainingNum;
    }
}
