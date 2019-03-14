package com.jmyp.service;

import com.jmyp.mapper.CategorysMapper;
import com.jmyp.pojo.Categorys;
import com.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by on 2018/12/17.
 */
@Service
public class CategorysService {

    //注入mapper接口代理对象
    @Autowired
    private CategorysMapper categorysMapper;


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
    public BaseResult categorysList(){
        try {
            //1，先查询顶级菜单
            //创建example对象
            Example example1 = new Example(Categorys.class);
            Example.Criteria criteria1 = example1.createCriteria();
            //设置查询参数
            criteria1.andEqualTo("parent_id",0);

            //执行查询
            List<Categorys> categorys1List = categorysMapper.selectByExample(example1);

            //创建一个集合对象，封装菜单数据
            //List categoryList = new ArrayList();

            //查询二级菜单
            //3，循环顶级菜单，根据顶级菜单id查询子节点
            for (Categorys categorys1 : categorys1List) {
                //创建example对象
                Example example2 = new Example(Categorys.class);
                Example.Criteria criteria2 = example2.createCriteria();
                //设置查询参数
                criteria2.andEqualTo("parent_id",categorys1.getId());
                //执行查询
                List<Categorys> categorys2List = categorysMapper.selectByExample(example2);

                //把集合放入categorys1
                categorys1.setChrildren(categorys2List);

                //5，循环二级菜单，根据二级菜单的id查询三级菜单
                for (Categorys categorys2 : categorys2List) {

                    //创建example对象
                    Example example3 = new Example(Categorys.class);
                    Example.Criteria criteria3 = example3.createCriteria();
                    //设置查询参数
                    criteria3.andEqualTo("parent_id",categorys2.getId());
                    //执行查询
                    List<Categorys> categorys3List = categorysMapper.selectByExample(example3);

                    //放入categorys2对象
                    categorys2.setChrildren(categorys3List);

                }


            }
            //查询成功
            return new BaseResult(0,"查询成功",null,categorys1List);

        } catch (Exception e) {
            e.printStackTrace();

            //查询失败
            return new BaseResult(1,"查询失败",null,null);
        }


    }
}
