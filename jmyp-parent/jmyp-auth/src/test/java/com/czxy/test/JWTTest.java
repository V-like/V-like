package com.czxy.test;

import com.jmyp.utils.JwtUtils;
import com.jmyp.utils.UserInfo;
import org.bouncycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.junit.Test;

/**
 * Created by on 2018/12/29.
 */
public class JWTTest {


    @Test
    public void generationToken() throws Exception{

        //调用工具类生成token
        //参数1：载和（用户信息：用户名，用户id）
        //参数2：签名
        //参数3：token过期时间
        String token = JwtUtils.generateToken(new UserInfo(9, "jack"), "czxy", 6);
        //打印token
        System.out.println("生成秘钥:"+ token);



    }

    /**
     * 需求：解析token
     */
    @Test
    public void parseToken() throws Exception{

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OSwidXNlcm5hbWUiOiJqYWNrIiwiZXhwIjoxNTQ2MDQ0MDE4fQ.4KTlbvHHd4SEZrO_kABxdBiMVhyq0YbQkPygfFqC9oY";

        //调用工具类对象，解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, "czxy1111");

        //打印结果
        System.out.println("用户id:"+user.getId());
        System.out.println("用户姓名:"+user.getUsername());

    }

}
