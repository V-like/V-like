package com.jmyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by on 2019/1/2.
 */
@Service
public class CartService {


    //注入redis模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 需求：查询购物车列表
     * 请求：/cartList
     * 参数：map
     * 返回值：List<Map>
     * 1）获取token
     * 2) 解析token，获取用户id
     * 3）查询该用户购物车列表数据
     */
    public List<Map> cartList(Integer userId){
        List<Map> cartList = redisTemplate.boundHashOps(userId).values();
        return cartList;
    }
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
    public void addcart(Integer userId, Map cartMap,List<Map> cartList) {

        Integer skuId = (Integer) cartMap.get("skuid");
        //循环购物车列表，判断是否有相同的商品
        for (Map cmap : cartList) {
            //判断商品id是否存在相同的情况，如果存在，表示有相同的商品
            Boolean hasKey = redisTemplate.boundHashOps(userId).hasKey(skuId);

            //判断是否存在相同
            if(hasKey){
                //获取商品数量
                Integer count = (Integer) cmap.get("count");
                //数量相加
                count += (Integer)cartMap.get("count");
                //添加
                cmap.put("count",count);

                //添加redis购物车
                redisTemplate.boundHashOps(userId).put(skuId,cmap);

                break;
            }

        }

        //4）否则，直接把商品添加到购物车中即可
        redisTemplate.boundHashOps(userId).put(skuId,cartMap);

    }
}
