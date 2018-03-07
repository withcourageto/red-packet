package top.cmoon.practice.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.cmoon.practice.activity.dao.UserDao;
import top.cmoon.practice.activity.po.UserInfo;

/**
 * Created by cool_moon on 2018/3/2.
 */
@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/login")
    public Object login(@RequestBody UserInfo userInfo, HttpServletRequest request) {

        UserInfo dbUser = userDao.findFirstByUsername(userInfo.getUsername());

        if (dbUser == null) {
            return "user not found";
        }

        request.getSession().setAttribute("user", dbUser);
        return "ok";
    }
}
