package com.jmyp.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.jmyp.mapper.*;
import com.jmyp.pojo.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by on 2018/12/25.
 */
@Service
public class SpuService {


    //注入spumapper
    @Autowired
    private SpuMapper spuMapper;

    //注入分类mapper接口代理对象
    @Autowired
    private CategoryMapper categoryMapper;

    //注入skumapper接口代理对象
    @Autowired
    private SkuMapper skuMapper;

    //注入相册mapper
    @Autowired
    private SkuPhotoMapper skuPhotoMapper;

    /**
     * 需求：根据spuid查询商品详情信息
     * 参数：Integer spu_id
     * 返回值：Map
     * 方法名：findItem
     * 查询流程：
     * 1，先查询三级分类
     * 2,根据默认的skuid,查询sku基本数据
     * 3,展示规格列表
     * 4,展示默认商品选中状态
     * 5,相册图片数据展示
     *
     *
     */
    public Map findItem(Integer spu_id){
        //1，先查询三级分类
        //1-1: 根据spuid查询商品信息
        Spu spu = spuMapper.selectByPrimaryKey(spu_id);
        //1-2: 查询一级分类
        Categorys categorys1 = categoryMapper.selectByPrimaryKey(spu.getCat1_id().longValue());
        //1-3: 查询二级分类
        Categorys categorys2 = categoryMapper.selectByPrimaryKey(spu.getCat2_id().longValue());
        //1-4: 查询三级分类
        Categorys categorys3 = categoryMapper.selectByPrimaryKey(spu.getCat3_id().longValue());

        //创建map对象，把分类放入map中
        Map<String,Object> itemMap = new LinkedHashMap<>();
        itemMap.put("cat1_info",categorys1);
        itemMap.put("cat2_info",categorys2);
        itemMap.put("cat3_info",categorys3);


        //2,根据默认的skuid,查询sku基本数据

        //2-1：获取sku_id
        Integer sku_id = spu.getSku_id();
        //2-2: 查询sku数据
        Sku sku = skuMapper.selectByPrimaryKey(sku_id);

        //2-3: 把数据放入map
        itemMap.put("spuid",spu_id);
        itemMap.put("skuid",sku_id);
        itemMap.put("goods_name",sku.getSku_name());
        itemMap.put("shop_price",sku.getPrice());
        itemMap.put("market_price",sku.getPrice());
        itemMap.put("on_sale_date",spu.getOn_sale_time());
        itemMap.put("comment_count",9999);
        itemMap.put("comment_level",1);
        //商品描述
        itemMap.put("description",spu.getDescription());
        itemMap.put("aftersale",spu.getAftersale());
        itemMap.put("stock",sku.getStock());


        //3,展示规格列表
        //3-1:从spu中获取规格列表数据
        String spec_list = spu.getSpec_list();
        //3-2:把json字符串转换为对象
        List list = JSON.parseObject(spec_list, List.class);
        //3-3:把查询结果放入map
        itemMap.put("spec_list",list);


        //4,展示商品选中状态
        //4-1:获取sku数据选中规格id数据
        String skuList = sku.getSpec_list();
        //4-2:获取sku数据选中规格数据
        String sku_list_code = sku.getSpec_list_code();

        //4-3:创建map
        Map skuMap = new HashMap();
        skuMap.put("id_list",skuList);
        skuMap.put("id_text",sku_list_code);

        //4-4: 把skumap放入itemmap
        itemMap.put("spec_info",skuMap);


        // 5,相册图片数据展示
        //5-1: 根据外键sku-id查询此商品相册图片
        Example example = new Example(SkuPhoto.class);
        //创建criteria对象
        Example.Criteria criteria = example.createCriteria();
        //设置查询参数
        criteria.andEqualTo("sku_id",sku_id);
        //执行查询
        List<SkuPhoto> skuPhotos = skuPhotoMapper.selectByExample(example);


        //logo对象
        //判断集合是否存在数据
        if(skuPhotos!=null && skuPhotos.size()>0){
            //创建map对象
            Map logoMap = new HashMap();
            //创建map对象
            Map photosMap = new HashMap();
            //创建集合对象
            List<Map> photosList = new ArrayList<>();
            //photos集合
            for (SkuPhoto photo : skuPhotos) {
                //判断小图，中图，大图
                if(photo.getType()==0){
                    logoMap.put("smlogo",photo.getUrl());
                    photosMap.put("smlogo",photo.getUrl());
                }else if(photo.getType()==1){
                    logoMap.put("biglogo",photo.getUrl());
                    photosMap.put("biglogo",photo.getUrl());
                }else if(photo.getType()==2){
                    logoMap.put("xbiglogo",photo.getUrl());
                    photosMap.put("xbiglogo",photo.getUrl());
                }


                //把map对象放入集合
                photosList.add(photosMap);


            }

            //把集合数据放入返回值map
            //把对象放入返回值map
            itemMap.put("logo",logoMap);
            itemMap.put("photos",photosList);
            
        }

        return itemMap;


    }



    //注入印象mapper接口代理对象
    @Autowired
    private ImpressionMapper impressionMapper;

    //注入评论mapper
    @Autowired
    private CommentsMapper commentsMapper;

    //注入用户mapper接口代理对象
    @Autowired
    private UserMapper userMapper;


    /**
     * 需求：查询商品详情评论数据
     * 请求：/comments
     * 参数：Map
     * 返回值：Map
     * 方法名：commentsList
     * 业务流程：
     * 1，查询商品印象表----根据spuid查询
     * 2，查询商品评论表----根据spuid查询
     */
    public Map commentsList(Map commentsMap){

        //创建一个map对象，封装查询结果
        Map<String,Object> maps = new HashMap<>();

        //1，查询商品印象表----根据spuid查询
        //1-1:获取spuid参数
        Integer spuid = (Integer) commentsMap.get("spuid");
        //1-2:根据spuid查询商品印象表数据
        //创建example对象
        Example example1 = new Example(Impression.class);
        Example.Criteria criteria1 = example1.createCriteria();
        //设置查询参数
        criteria1.andEqualTo("spu_id",spuid);

        List<Impression> impressions = impressionMapper.selectByExample(example1);
        //1-3:把数据封装到map对象
        maps.put("impressions",impressions);


        //2，查询商品评论表----根据spuid查询

        //1 ） 查询好评数，中评数，差评数
        // 0好评 1 中评 2 差评
        // 查询好评数量
        //创建example对象
        Example example2 = new Example(Comment.class);
        Example.Criteria criteria2 = example2.createCriteria();
        //设置查询参数
        criteria2.andEqualTo("ratio",0);
        criteria2.andEqualTo("spu_id",spuid);
        //好评
        int goodcomments = commentsMapper.selectCountByExample(example2);
        
        
        //创建example对象
        Example example3 = new Example(Comment.class);
        Example.Criteria criteria3 = example3.createCriteria();
        //设置查询参数
        criteria3.andEqualTo("ratio",1);
        criteria3.andEqualTo("spu_id",spuid);
        //中评
        int commoncomments = commentsMapper.selectCountByExample(example3);

        //创建example对象
        Example example4 = new Example(Comment.class);
        Example.Criteria criteria4 = example4.createCriteria();
        //设置查询参数
        criteria4.andEqualTo("ratio",2);
        criteria4.andEqualTo("spu_id",spuid);
        //差评
        int badcomments = commentsMapper.selectCountByExample(example4);

        //创建map对象，封装好评，中评，差评数据
        Map commentMap =new HashMap();
        commentMap.put("goods",goodcomments);
        commentMap.put("common",commoncomments);
        commentMap.put("bad",badcomments);

        //把map放入maps
        maps.put("ratio",commentMap);


        //2)把评论的总数量进行统计
        maps.put("comment_count",goodcomments+commoncomments+badcomments);

        //3)查询评论数据
        //根据spuid查询当前商品的评论数据
        //创建example对象
        Example example5 = new Example(Comment.class);
        Example.Criteria criteria5 = example5.createCriteria();
        //设置查询参数
        criteria5.andEqualTo("spu_id",spuid);
        //执行查询
        List<Comment> commentList = commentsMapper.selectByExample(example5);

        //循环集合，查询出每一个评论对应的用户数据
        for (Comment comment : commentList) {
            //根据主键查询用户
            //获取用户主键
            Integer user_id = comment.getUser_id();
            //根据用户id查询
            User user = userMapper.selectByPrimaryKey(user_id);

            //把对象设置到集合
            comment.setUser(user);
        }


        //把评论数据放入map
        maps.put("comments",commentList);

        return maps;
    }


}
