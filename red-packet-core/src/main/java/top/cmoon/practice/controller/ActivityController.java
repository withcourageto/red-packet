package top.cmoon.practice.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cmoon.practice.activity.po.UserInfo;
import top.cmoon.practice.activity.service.ActivityService;
import top.cmoon.practice.redpacket.service.TakeResponse;

/**
 * Created by cool_moon on 2018/3/2.
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @RequestMapping(value = "/red_packet/{activityId}")
    public Object takeRedPacket(@PathVariable int activityId, HttpServletRequest request) {

        UserInfo userInfo = getUser(request);

        if (userInfo == null) {
            TakeResponse response = new TakeResponse();
            response.setWinning(false);
            response.setMsg("您没有登陆");
            return response;
        }

        int userId = userInfo.getId();
        TakeResponse takeResponse = activityService.takeRedPacket(activityId, userId);
        return takeResponse;
    }


    public UserInfo getUser(HttpServletRequest request) {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        return user;
    }


}
