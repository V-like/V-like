package com.jmyp.service;

import com.jmyp.mapper.UserMapper;
import com.jmyp.pojo.User;
import com.jmyp.utils.JmypResult;
import com.jmyp.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by on 2018/12/12.
 */
@Service
@Transactional
public class UserService {

    //注入mapper接口代理对象
    @Autowired
    private UserMapper userMapper;

    //注入redis模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 需求：实现用户注册
     * 参数：
     * 手机号
     *
     * 注册业务分析：
     * 1，判断验证码是否存在
     * 2，判断验证码是否匹配
     * 3，判断用户密码是否正确
     *
     */
    public JmypResult register(User user){
        try {
            
            //先从redis中获取短信验证码
            String code = (String) redisTemplate.boundValueOps("sms_code" + user.getMobile()).get();

            //判断验证码是否存在
            if(code==null || code == ""){
                return new JmypResult(1,"验证码不存在");
            }

            //判断验证码是否存在
            if(!user.getCode().equals(code)){
                return new JmypResult(1,"验证码不正确");
            }

            //判断密码是否相等
            if(!user.getPassword().equals(user.getPassword_confirm())){
                return new JmypResult(1,"2此密码不相等");
            }

            //创建时间
            Date date = new Date();
            user.setCreatedAt(date);
            user.setUpdatedAt(date);

            //注册
            userMapper.insertSelective(user);

            //成功
            return new JmypResult(0,"注册成功");

        } catch (Exception e) {
            e.printStackTrace();

            //失败
            return new JmypResult(1,"注册失败");
        }
    }

    /**
     * 需求：用户实现注册-- 发现短信验证码进行注册认证
     * 请求：/sms
     * 返回值：JmypResult
     * 方法名：sendSms
     *
     * 发送短信业务流程：
     * 1，导入发送短信工具类
     * 2，生成6位数字的验证码
     * 3，把验证码存储到redis服务器
     * 4，给验证码设置过期时间
     * 5，发送短信
     * 6，返回成功，或失败信息
     */
    public JmypResult sendSms(User user) {

        try {
            //2，生成6位数字的验证码
            //生成一个大于0，小于1的随机数
            String code =  (long)(Math.random()*1000000)+"";

            //3，把验证码存储到redis服务器
            redisTemplate.boundValueOps("sms_code"+user.getMobile()).set(code);

            //4，给验证码设置过期时间
            redisTemplate.boundValueOps("sms_code"+user.getMobile()).expire(5, TimeUnit.MINUTES);

            //5，发送短信
            SmsUtil.sendSms(user.getMobile(),code);


            //发送成功
            return new JmypResult(0,"发送成功");

        } catch (Exception e) {
            e.printStackTrace();

            //发送失败
            return new JmypResult(1,"发送失败");
        }


    }
}
