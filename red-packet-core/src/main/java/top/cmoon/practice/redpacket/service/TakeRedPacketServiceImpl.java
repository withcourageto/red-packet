package top.cmoon.practice.redpacket.service;

import org.springframework.stereotype.Service;
import top.cmoon.practice.activity.domain.RedPacketPool;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Service
public class TakeRedPacketServiceImpl {


    private RedPacketCounterFacade redPacketCounterFacade;

    private ActivityFacade activityFacade;

    public TakeResponse takeRedPacket(ActivityContext context) {

        ActivityConfig activityConfig = context.getActivityConfig();

        RedPacketPool redPacketPool = activityConfig.chooseRedPacketPool(context);

        redPacketCounterFacade.incrementTryCount(context);

        RedPacketId redPacket = redPacketPool.randomTakeRedPacket();

        if (redPacket != null) {
            redPacketCounterFacade.decrementRedPacketCount(context);
        }

        return buildTakeTakeResponse(redPacket, context);
    }

    private TakeResponse buildTakeTakeResponse(RedPacketId redPacket, ActivityContext context) {

        TakeResponse takeResponse = new TakeResponse();
        takeResponse.setActivityId(context.getActivityId());

        if (redPacket == null) {
            return takeResponse;
        }

        takeResponse.setWinning(true);
        takeResponse.setRedPacketId(redPacket);
        return takeResponse;
    }


}
