package com.jmyp.controller;

import com.jmyp.service.SearchService;
import com.jmyp.vo.BaseResult;
import com.jmyp.vo.SearchMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by on 2018/12/21.
 */
@RestController
public class SearchController {

    //注入服务层对象
    @Autowired
    private SearchService searchService;



    /**
     * 需求：根据分类id查询品牌数据
     * 返回值：map
     * map封装集合：
     * 1，品牌集合
     * 2，规格集合
     */
    @RequestMapping("/conditionSearch")
    public Map searchCondition(Long cat_id){
        //调用服务层代码，查询结果
        Map map = searchService.findBrandAndSpecList(cat_id);
        return  map;
    }


    /**
     * 需求：根据分类id，品牌，价格区间，规格属性，分页
     * 请求：searchList
     * 参数：
     * SearchMap searchMap
     * 返回值：
     * BaseResult
     * 方法：searchList
     */
    @RequestMapping("/searchList")
    public BaseResult searchList(@RequestBody SearchMap searchMap){
        //调用服务层方法，实现搜索
        BaseResult result = searchService.searchList(searchMap);
        return  result;
    }

}
