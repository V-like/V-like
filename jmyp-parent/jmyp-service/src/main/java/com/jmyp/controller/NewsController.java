package com.jmyp.controller;

import com.jmyp.service.NewsService;
import com.jmyp.service.UserService;
import com.jmyp.vo.BaseResult;
import com.netflix.discovery.converters.Auto;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by on 2018/12/14.
 */
@RestController
public class NewsController {


    //注入服务层对象
    @Autowired
    private NewsService newsService;


    /**
     * 需求：查询快报信息
     * 参数：
     * 1、限制条数
     * 2、偏移量
     * 3、当前页
     * 4、每页显示的条数
     * 5、排序字段
     * 6、排序方式
     * 请求：/news
     * 返回值：BaseResult
     * 方法名：newsList
     */
    @RequestMapping("/news")
    public BaseResult newsList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "8") Integer rows,
                               String sort_by,
                               String sort_way){
        //调用服务层代码，实现分页查询快报信息
        BaseResult result = newsService.newsList(page, rows, sort_by, sort_way);
        return result;


    }


}
