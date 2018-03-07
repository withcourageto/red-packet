package top.cmoon.practice.test.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.cmoon.practice.test.po.UserInfo;
import top.cmoon.practice.test.repo.UserRepo;

/**
 * Created by cool_moon on 2018/3/2.
 */
@RestController
public class LoginController {


    @Autowired
    UserRepo userRepo;

    @PostMapping("/login")
    public Object login(@RequestBody UserInfo loginInfo, HttpServletRequest request) {

        UserInfo dbUser = userRepo.findFirstByUsername(loginInfo.getUsername());

        if (dbUser == null) {
            return "user name not found";
        }

        request.getSession().setAttribute("user", dbUser);
        return "ok";
    }

}
