package com.jmyp.service;

import com.jmyp.config.JwtConfig;
import com.jmyp.mapper.UserMapper;
import com.jmyp.pojo.User;
import com.jmyp.utils.JmypBaseResult;
import com.jmyp.utils.JwtUtils;
import com.jmyp.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/12/29.
 */
@Service
@EnableConfigurationProperties(JwtConfig.class)
public class AuthService {

    //注入usermapper接口代理对象
    @Autowired
    private UserMapper userMapper;

    //注入配置类对象
    @Autowired
    private JwtConfig jwtConfig;


    /**
     * 需求：颁发凭证（生成token）
     * 参数：用户名，密码
     * 返回值：token
     * 1,根据用户名查询数据库
     * 2,判断此用户是否存在
     * 3,如果用户存在，生成token
     * 4,返回token
     *
     */
    public JmypBaseResult generateToken(Map userMap) {

        try {

            //获取用户名
            String mobile  = (String)userMap.get("mobile");
            //获取密码
            String password  = (String)userMap.get("password");

            //判断用户是否存在
            //创建example对象
            Example example = new Example(User.class);
            //创建criteria对象
            Example.Criteria criteria = example.createCriteria();
            //设置查询参数
            criteria.andEqualTo("mobile", mobile);
            //执行查询
            List<User> users = userMapper.selectByExample(example);

            //判断用户是否存在
            if (users != null || users.size() > 0) {

                User user = users.get(0);
                //生成token
                String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getName()), jwtConfig.getKey(), jwtConfig.getTtl());

                return new JmypBaseResult(0,"颁发凭证成功",token);
            }
            //否则
            return new JmypBaseResult(1,"颁发凭证失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new JmypBaseResult(1,"颁发凭证失败",null);
        }


    }

}
