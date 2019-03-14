package com.jmyp.task;

import com.alibaba.fastjson.JSON;
import com.jmyp.dao.RepositoryDao;
import com.jmyp.mapper.ItemMapper;
import com.jmyp.es.EsData;
import com.jmyp.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2018/12/20.
 */
@Component
public class TaskService {

    //注入商品mapper接口代理对象
    @Autowired
    private ItemMapper itemMapper;

    //注入dao
    @Autowired
    private RepositoryDao repositoryDao;


    /**
     * 需求：定义索引入库
     * 方法：importDataToEsIndex
     * 入库业务步骤：
     * 1，查询出所有商品数据
     * 2，把商品数据映射到esData
     * 3,使用repositoryDao.saveALl保存
     * 4，入库成功
     *
     */
    @Scheduled(cron = "10 * * * * ?")
    public void importDataToEsIndex(){
        //1，查询出所有商品数据
        List<Item> itemList = itemMapper.selectAll();

        //创建一个集合，封装对象
        List<EsData> esList = new ArrayList<>();

        //2，把商品数据映射到esData
        //循环
        for (Item item : itemList) {
            //创建对象
            EsData data = new EsData();
            data.setId(item.getId());

            //spuid
            data.setSpu_id(item.getGoods_id());

            data.setCategory_name(item.getCategory());

            //分类id
            data.setCategory_id(item.getCategory_id());

            data.setBrand_name(item.getBrand());
            data.setTitle(item.getTitle());
            data.setSell_point(item.getSell_point());
            data.setPrice(item.getPrice());
            data.setImage(item.getImage());

            //导入规格属性
            Map map = JSON.parseObject(item.getSpec(), Map.class);
            //把map对象添加到映射对象中
            data.setSpecMap(map);


            data.setComment_num(item.getComment_num());




            esList.add(data);
        }
        //3,使用repositoryDao.saveALl保存
        repositoryDao.saveAll(esList);





    }


}
