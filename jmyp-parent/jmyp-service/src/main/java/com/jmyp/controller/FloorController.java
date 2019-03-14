package com.jmyp.controller;

import com.jmyp.service.FloorService;
import com.jmyp.vo.BaseResult;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by on 2018/12/14.
 */
@RestController
public class FloorController {

    //注入服务层对象
    @Autowired
    private FloorService floorService;

    /**
     * 需求：查询楼层广告数据
     * 请求：floors
     * 参数：无
     * 返回值：BaseResult
     * 方法名：floorList
     * 查询步骤：
     * 1，查询楼层表数据
     * 2，根据楼层id(是其他表的外键)查询tb_rec_sub_categorys选项卡
     * 3，再查询楼层分类选项卡数据tb_rec_categorys
     * 4，根据楼层选项卡的id查询楼层选项卡的商品内容
     * 5，根据楼层id查询推荐品牌
     * 6，根据楼层id查询推荐消息
     * 7，根据楼层id查询左右2层边栏广告
     */
    @RequestMapping("/floors")
    public BaseResult floorList(){

        //调用服务层代码，查询楼层信息
        BaseResult result = floorService.floorList();

        return  result;
    }


}
