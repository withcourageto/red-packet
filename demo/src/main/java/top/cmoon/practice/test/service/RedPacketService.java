package top.cmoon.practice.test.service;

import top.cmoon.practice.test.po.Activity;
import top.cmoon.practice.test.vo.TakeResponse;

/**
 * Created by cool_moon on 2018/2/28.
 */
public interface RedPacketService {


    TakeResponse takeRedPacket(Activity activity, Integer userId);


    void decrementRedPacketNum(int redPacketId, int redPacketPoolId);
}
