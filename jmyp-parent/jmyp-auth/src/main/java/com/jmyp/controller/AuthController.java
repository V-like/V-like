package com.jmyp.controller;

import com.jmyp.config.JwtConfig;
import com.jmyp.service.AuthService;
import com.jmyp.utils.JmypBaseResult;
import com.jmyp.utils.JwtUtils;
import com.jmyp.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by on 2018/12/29.
 */
@RestController
public class AuthController {

    //注入service对象
    @Autowired
    private AuthService authService;

    /**
     * 需求：颁发凭证
     * 参数：Map
     * 返回值：token
     */
    @RequestMapping("/token")
    public JmypBaseResult generateToken(@RequestBody Map userMap){
        //调用service服务方法
        JmypBaseResult result = authService.generateToken(userMap);
        return result;
    }

    /**
     * 需求：验证token凭证
     */
    @RequestMapping("/verify")
    public UserInfo verify(HttpServletRequest request){

        try {
            //从header请求头中获取token
            String token = request.getHeader("Authorization");
            //解析
            UserInfo user = JwtUtils.getInfoFromToken(token, "czxy");

            return  user;
        } catch (Exception e) {
            e.printStackTrace();

            return  null;
        }


    }
}
