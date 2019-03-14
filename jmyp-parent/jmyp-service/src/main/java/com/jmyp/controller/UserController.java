package com.jmyp.controller;

import com.jmyp.pojo.User;
import com.jmyp.service.UserService;
import com.jmyp.utils.JmypResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by on 2018/12/12.
 */
@RestController
public class UserController {

    //注入服務層對象
    @Autowired
    private UserService userService;

    /**
     * 需求：用戶註冊
     * 參數：User user
     * 返回值：
     * json格式JmypResult
     * 方法名：resgister
     */
    @RequestMapping("/register")
    public JmypResult register(@RequestBody User user){
        JmypResult result = userService.register(user);
        return  result;
    }


    /**
     * 需求：用户实现注册-- 发现短信验证码进行注册认证
     * 请求：/sms
     * 返回值：JmypResult
     * 方法名：sendSms
     */
    @RequestMapping("/sms")
    public JmypResult sendSms(@RequestBody User user){
        //调用服务层方法，发送短信
        JmypResult result = userService.sendSms(user);
        return  result;

    }

}
