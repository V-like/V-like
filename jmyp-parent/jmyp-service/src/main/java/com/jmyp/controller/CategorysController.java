package com.jmyp.controller;

import com.jmyp.service.CategorysService;
import com.jmyp.service.FloorService;
import com.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by on 2018/12/14.
 */
@RestController
public class CategorysController {

    //注入服务层对象
    @Autowired
    private CategorysService categorysService;

    /**
     * 需求：查询首页左侧菜单
     * 参数：无
     * 请求：/index/categorys
     * 返回值：BaseResult
     * 方法名：categorysList
     * 业务流程：
     * 1，先查询顶级菜单
     * 2，把菜单封装到集合中
     * 3，循环顶级菜单，根据顶级菜单id查询子节点
     * 4，把查询出来的集合再次进行封装
     * 5，循环二级菜单，根据二级菜单的id查询三级菜单
     * 6，把三级菜单数据封装到集合
     */
    @RequestMapping("/categorys")
    public BaseResult categorysList(){

        //调用服务层代码，查询楼层信息
        BaseResult result = categorysService.categorysList();

        return  result;
    }


}
