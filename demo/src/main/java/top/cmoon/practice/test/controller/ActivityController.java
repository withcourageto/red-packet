package top.cmoon.practice.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import top.cmoon.practice.test.po.Activity;
import top.cmoon.practice.test.service.ActivityService;
import top.cmoon.practice.test.vo.ResponseBody;
import top.cmoon.practice.test.vo.TakeResponse;
import top.cmoon.practice.test.po.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by cool_moon on 2018/2/28.
 */

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ActivityService activityService;


    @GetMapping("/redPacket/{activityId}")
    public Object takeRedPacket(@PathVariable int activityId, HttpServletRequest request) {

        Integer userId = currentUser(request);
        if (userId == null) {
            return notLoginTipMsg();
        }

        TakeResponse take = activityService.takeRedPacket(activityId, userId);
        return take;
    }


    @GetMapping("/async/redPacket/{activityId}")
    public DeferredResult take(@PathVariable int activityId, HttpServletRequest request) {
        DeferredResult<Object> result = new DeferredResult<>();
        Integer userId = currentUser(request);
        if (userId == null) {
            notLoginTipMsg(result);
            return result;
        }

        try {
            taskExecutor.submit(() -> {
                TakeResponse take = activityService.takeRedPacket(activityId, userId);
                result.setResult(take);
            });
        } catch (TaskRejectedException rx) {
            serverBusyTipMsg(result); // 排队请求超过最大数量
            return result;
        }

        result.onTimeout(() -> {
            serverBusyTipMsg(result);   // 请求处理超时
        });

        return result;
    }

    private void serverBusyTipMsg(DeferredResult<Object> result) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setCode(504);
        responseBody.setMsg("请稍后重试");

        result.setResult(responseBody);
    }

    private ResponseBody notLoginTipMsg() {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setCode(401);
        responseBody.setMsg("未登陆");
        return responseBody;
    }

    private void notLoginTipMsg(DeferredResult<Object> result) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setCode(401);
        responseBody.setMsg("未登陆");

        result.setResult(responseBody);
    }


    @PostMapping
    public Object addActivity(@RequestBody Activity activity) {

        activityService.addAndInitActivity(activity);

        ResponseBody responseBody = new ResponseBody();

        responseBody.setCode(0);
        responseBody.setMsg("ok");
        return responseBody;
    }


    private Integer currentUser(HttpServletRequest request) {
        Optional<UserInfo> optional = Optional.of((UserInfo) request.getSession().getAttribute("user"));

        return optional.orElse(new UserInfo()).getId();
    }

}
