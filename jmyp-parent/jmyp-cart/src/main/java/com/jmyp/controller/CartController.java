package com.jmyp.controller;

import com.jmyp.service.CartService;
import com.jmyp.utils.JmypResult;
import com.jmyp.utils.JwtUtils;
import com.jmyp.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2019/1/2.
 */
@RestController
public class CartController {

    //注入购物车服务对象
    @Autowired
    private CartService cartService;


    /**
     * 需求：添加购物车
     * 请求：/addcart
     * 参数：map
     * 返回值：JmypResult
     * 1）查询购物车列表
     * 2）判断购物车列表中是否存在相同的商品
     * 3）如果存在相同的商品，此商品数量相加
     * 4）否则，直接把商品添加到购物车中即可
     */
    @RequestMapping("/addcart")
    public JmypResult addcart(@RequestBody Map cartMap, HttpServletRequest request){

        try {
            // 1）查询购物车列表
            List<Map> cartList = this.cartList(request);
            // 2) 获取token
            String token = request.getHeader("Authorization");
            //3) 解析token
            UserInfo user = JwtUtils.getInfoFromToken(token, "czxy");
            //4）调用服务层方法，实现购物车添加
            cartService.addcart(user.getId(),cartMap,cartList);

            //添加成功
            return new JmypResult(0,"添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            //添加失败
            return new JmypResult(1,"添加失败");
        }

    }


    /**
     * 需求：查询购物车列表
     * 请求：/cartList
     * 参数：map
     * 返回值：List<Map>
     * 1）获取token
     * 2) 解析token，获取用户id
     * 3）查询该用户购物车列表数据
     */
    @RequestMapping("/cartList")
    public List<Map> cartList(HttpServletRequest request){
        try {
            //1）获取token
            String token = request.getHeader("Authorization");
            //2) 解析token，获取用户id
            UserInfo user = JwtUtils.getInfoFromToken(token, "czxy");

            Integer userId = user.getId();

            //3）查询该用户购物车列表数据
            List<Map> cartList = cartService.cartList(userId);

            return cartList;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return  null;
    }

}
