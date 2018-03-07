package top.cmoon.practice.test.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by cool_moon on 2018/2/28.
 */

@Entity
@Table(name = "r_activity")
public class Activity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "red_packet_nums")
    private Integer redPacketNums;

    @Column(name = "red_packet_remain_nums")
    private Integer redPacketRemainNums;

    @Column(name = "user_max_num")
    private Integer userMaxNum;


    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

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

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }


    @Transient
    public boolean isStart() {
        return this.getStartTime().isAfter(LocalDateTime.now());
    }

    @Transient
    public boolean isEnd() {
        return this.getEndTime().isBefore(LocalDateTime.now());

    }

    @Transient
    public boolean noRedPacket() {

        return this.redPacketRemainNums <= 0;
    }

    public Integer getUserMaxNum() {
        return userMaxNum;
    }

    public void setUserMaxNum(Integer maxNum) {
        this.userMaxNum = maxNum;
    }
}
