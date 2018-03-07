package top.cmoon.practice.test.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.cmoon.practice.test.event.WinRedPacketEvent;

/**
 * Created by cool_moon on 2018/3/3.
 */
@Component
public class WinRedPacketEventListener implements ApplicationListener<WinRedPacketEvent> {

    @Override
    public void onApplicationEvent(WinRedPacketEvent winRedPacketEvent) {

        StringBuilder info = new StringBuilder("================红包获得====================\n");
        info.append("用户：")
                .append(winRedPacketEvent.getUserId())
                .append(" 获得红包 ")
                .append(winRedPacketEvent.getRedPacket().getMoney())
                .append(" 元");
        System.out.println(info);
    }
}
