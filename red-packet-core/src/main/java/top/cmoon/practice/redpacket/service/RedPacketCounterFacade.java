package top.cmoon.practice.redpacket.service;

/**
 * Created by cool_moon on 2018/2/28.
 */
public interface RedPacketCounterFacade {
    void incrementTryCount(ActivityContext context);

    void decrementRedPacketCount(ActivityContext context);
}
