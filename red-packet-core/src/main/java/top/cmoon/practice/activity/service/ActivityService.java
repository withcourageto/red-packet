package top.cmoon.practice.activity.service;

import top.cmoon.practice.activity.po.WinRedPacketLogPO;
import top.cmoon.practice.redpacket.service.TakeResponse;

/**
 * Created by cool_moon on 2018/3/2.
 */
public interface ActivityService {
    TakeResponse takeRedPacket(int activityId, int userId);

    void saveWinningLog(WinRedPacketLogPO winRedPacketLog);

    void decrementRemainingRedPackedNum(int activityId);
}
