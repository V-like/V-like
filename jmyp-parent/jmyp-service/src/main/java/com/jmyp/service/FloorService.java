package com.jmyp.service;

import com.jmyp.mapper.*;
import com.jmyp.pojo.*;
import com.jmyp.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by on 2018/12/14.
 */
@Service
public class FloorService {

    //注入楼层mapper接口代理对象
    @Autowired
    private RecFloorMapper floorMapper;

    //注入楼层选项卡
    @Autowired
    private RecSubCategorysMapper subCategorysMapper;

    //注入楼层分类选项卡
    @Autowired
    private RecCategorysMapper categorysMapper;

    //注入楼层商品接口代理对象
    @Autowired
    private RecGoodsMapper recGoodsMapper;


    //注入推荐品牌对象
    @Autowired
    private RecBrandMapper brandMapper;

    //注入消息对象
    @Autowired
    private RecNewsMapper newsMapper;
    //注入广告对象
    @Autowired
    private RecAdMapper adMapper;


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
    public BaseResult floorList(){
        try {
            //1，查询楼层表数据
            List<RecFloors> floorsList = floorMapper.selectAll();

            //2，根据楼层id(是其他表的外键)查询tb_rec_sub_categorys选项卡
            //2-1:循环楼层集合
            for (RecFloors recFloors : floorsList) {

                //获取楼层id
                Integer id = recFloors.getId();

                //2-2:创建example对象，根据外键查询选项卡数据
                Example example1 = new Example(RecSubCategorys.class);
                //创建criteria对象
                Example.Criteria criteria1 = example1.createCriteria();
                //设置查询参数
                criteria1.andEqualTo("floor_id",id);

                //执行查询
                List<RecSubCategorys> subCategorysList = subCategorysMapper.selectByExample(example1);

                //把选项标签放入楼层对象
                recFloors.setSubCategorys(subCategorysList);


                //3，再查询楼层分类选项卡数据tb_rec_categorys

                //3-1:创建example对象，根据外键查询选项卡数据
                Example example2 = new Example(RecCategorys.class);
                //创建criteria对象
                Example.Criteria criteria2 = example2.createCriteria();
                //设置查询参数
                criteria2.andEqualTo("floor_id",id);

                //执行查询
                List<RecCategorys> categorysList = categorysMapper.selectByExample(example2);
                //把数据设置到楼层对象
                recFloors.setRecCategorys(categorysList);

                //遍历选项卡数据，查询每一个选项卡下面对应对的商品对象
                for (RecCategorys recCategorys : categorysList) {

                    //创建example对象，根据外键查询选项卡数据
                    Example example3 = new Example(RecGoods.class);
                    //创建criteria对象
                    Example.Criteria criteria3 = example3.createCriteria();
                    //设置查询参数
                    criteria3.andEqualTo("rec_id",recCategorys.getId());

                    //执行查询
                    List<RecGoods> goodsList = recGoodsMapper.selectByExample(example3);
                    //把商品集合设置到选项卡对象
                    recCategorys.setGoods(goodsList);


                }

               // 5，根据楼层id查询推荐品牌
                //创建example对象，根据外键查询选项卡数据
                Example example4 = new Example(RecBrand.class);
                //创建criteria对象
                Example.Criteria criteria4 = example4.createCriteria();
                //设置查询参数
                criteria4.andEqualTo("floor_id",id);

                //执行查询
                List<RecBrand> brandList = brandMapper.selectByExample(example4);
                //把品牌列表设置到楼层对象
                recFloors.setBrands(brandList);



               // 6，根据楼层id查询推荐消息
                //创建example对象，根据外键查询选项卡数据
                Example example5 = new Example(RecNews.class);
                //创建criteria对象
                Example.Criteria criteria5 = example5.createCriteria();
                //设置查询参数
                criteria5.andEqualTo("floor_id",id);

                //执行查询
                List<RecNews> newsList = newsMapper.selectByExample(example5);
                //把品牌列表设置到楼层对象
                recFloors.setRecNews(newsList);


                //7，根据楼层id查询左右2层边栏广告

                //先查询左侧广告
                //创建example对象，根据外键查询选项卡数据
                Example example6 = new Example(RecAd.class);
                //创建criteria对象
                Example.Criteria criteria6 = example6.createCriteria();
                //设置查询参数
                criteria6.andEqualTo("floor_id",id);
                //查询左侧广告
                //type 1: 左侧  2： 右侧
                criteria6.andEqualTo("type",1);


                RecAd leftAd = null;

                //执行查询
                List<RecAd> leftList = adMapper.selectByExample(example6);

                //判断集合是否存在广告
                if(leftList!=null && leftList.size()>0){
                    leftAd =  leftList.get(0);
                }

                //把左侧广告设置到楼层对象
                recFloors.setLeftAd(leftAd);


                //在查询右侧广告
                //创建example对象，根据外键查询选项卡数据
                Example example7 = new Example(RecAd.class);
                //创建criteria对象
                Example.Criteria criteria7 = example7.createCriteria();
                //设置查询参数
                criteria7.andEqualTo("floor_id",id);
                //查询左侧广告
                //type 1: 左侧  2： 右侧
                criteria7.andEqualTo("type",2);


                RecAd rightAd = null;

                //执行查询
                List<RecAd> rightList = adMapper.selectByExample(example6);

                //判断集合是否存在广告
                if(rightList!=null && rightList.size()>0){
                    rightAd =  rightList.get(0);
                }

                //把左侧广告设置到楼层对象
                recFloors.setRightAd(rightAd);



            }



            //查询成功
            return new BaseResult(0,
                    "查询成功",
                    null,
                    floorsList);
        } catch (Exception e) {
            e.printStackTrace();
            //查询成功
            return new BaseResult(1,
                    "查询失败",
                    null,
                    null);
        }


    }




}
