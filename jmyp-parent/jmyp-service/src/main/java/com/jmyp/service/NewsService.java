package com.jmyp.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jmyp.mapper.NewsMapper;
import com.jmyp.pojo.News;
import com.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by on 2018/12/14.
 */
@Service
public class NewsService {


    //注入mapper接口代理对象
    @Autowired
    private NewsMapper newsMapper;

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
     *
     * 导入分页坐标依赖：PageHelper
     *
     * 分页查询快报信息流程：
     * 1，创建news 的 example对象
     * 2，创建criteria对象，设置查询条件
     * 3，判断排序字段是否为空
     * 4，判断排序方式是否为空
     * 5，设置分页查询
     * 6，返回查询结果
     *
     */
    public BaseResult newsList(Integer page, Integer rows,String sort_by,String sort_way){
        try {
            // 1，创建news 的 example对象
            Example example = new Example(News.class);
            //2，创建crritria对象，设置查询条件
            Example.Criteria criteria = example.createCriteria();
            //3，判断排序字段是否为空
            if(sort_by!=null && !"".equals(sort_by)){
                //根据时间排序
                example.setOrderByClause("created_at desc");
            }else{
                example.setOrderByClause("updated_at desc");
            }

            //4，判断排序方式是否为空
            if(sort_way!=null && !"".equals(sort_way)){
                example.setOrderByClause("created_at"+" "+sort_way);
            }

            //5，设置分页查询
            PageHelper.startPage(page,rows);

            //6,执行查询
            Page<News> pages = (Page<News>) newsMapper.selectByExample(example);

            //7,返回结果: 查询成功
            return new BaseResult(0,"查询成功",pages.getTotal(),pages.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            //返回失败信息
            return new BaseResult(1,"查询失败",null,null);
        }

    }

}
