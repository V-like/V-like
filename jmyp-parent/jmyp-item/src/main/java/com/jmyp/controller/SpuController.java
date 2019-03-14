package com.jmyp.controller;

import com.jmyp.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by on 2018/12/25.
 */
@RestController
public class SpuController {

    //注入service对象
    @Autowired
    private SpuService spuService;

    /**
     * 需求：根据spuid查询商品详情信息
     * 请求：/goods
     * 参数：Integer spu_id
     * 返回值：Map
     * 方法名：findItem
     * 查询流程：
     * 1，先查询三级分类
     *
     */
    @RequestMapping("/goods")
    public Map findItem(Integer spu_id){
        Map map = spuService.findItem(spu_id);
        return  map;
    }

    /**
     * 需求：查询商品详情评论数据
     * 请求：/comments
     * 参数：Map
     * 返回值：Map
     * 方法名：commentsList
     */
    @RequestMapping("/comments")
    public Map commentsList(@RequestBody Map commentsMap){
        //调用service方法
        Map map = spuService.commentsList(commentsMap);
        return map;
    }

}
