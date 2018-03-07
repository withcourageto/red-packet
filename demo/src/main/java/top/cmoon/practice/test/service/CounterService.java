package top.cmoon.practice.test.service;

import org.springframework.stereotype.Service;
import top.cmoon.practice.test.po.Activity;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Service
public class CounterService {
    public void incrementTryCount(int activityId) {
    }

    public boolean reachLimit(Activity activity, Integer userId, int limit) {
        return false;
    }
}
