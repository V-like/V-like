/*
package com.jmyp.test;

import com.jmyp.es.EsData;
import com.jmyp.SearchApplication;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by on 2018/12/18.
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class EsCRUD {

    //注入es模板对象
    @Autowired
    private ElasticsearchTemplate esTemplate;

*/
/*

    //注入dao对象
    @Autowired
    private RepositoryDao repositoryDao;


    *//*

*/
/**
     * 需求：添加索引库
     *//*
*/
/*

    @Test
    public void addIndex(){
        //使用es模板对象，添加索引库即可
        //创建索引，会根据Item类的@Document注解信息来创建
        esTemplate.createIndex(Item.class);
        //配置映射，会根据Item类中的id、Field等字段来自动完成映射
        esTemplate.putMapping(Item.class);
    }

    *//*

*/
/**
     * 需求：索引库删除
     *//*
*/
/*

    @Test
    public void deleteIndex(){
        //根据class类删除
       //esTemplate.deleteIndex(Item.class);
        //根据索引库名称来删除
        esTemplate.deleteIndex("item");
    }


    *//*

*/
/**
     * 需求:使用repositoryDAO添加索引库
     *//*
*/
/*

    @Test
    public void addIndexData(){
        //创建商品对象，给模拟数据
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        //完成索引库添加
        repositoryDao.save(item);
    }

    @Test
    public void addIndexData1(){
        //创建商品对象，给模拟数据
        Item item = new Item();
        item.setId(100000L);
        item.setTitle("美团手机");
        item.setBrand("美团");
        item.setCategory("手机");
        item.setPrice(9999d);
        item.setImages("http://");

        //创建
        Map<String,Object> specMap = new HashMap<>();
        specMap.put("内存","4G");
        specMap.put("颜色","红色");
        //设置值
        item.setSpecMap(specMap);
        //完成索引库添加
        repositoryDao.save(item);
    }


    *//*

*/
/**
     * 需求:使用repositoryDAO添加索引库
     *//*
*/
/*

    @Test
    public void addMoreIndexData(){

        //创建集合，封装需要添加的商品数据
        List<Item> itemList =  new ArrayList<>();

        //模拟多个item对象
        //创建商品对象，给模拟数据
        Item item1 = new Item(1L, "小米手机", "手机",
                "小米", 29.00, "http://image.baidu.com/13123.jpg");
        //把商品添加到集合
        itemList.add(item1);

        //创建商品对象，给模拟数据
        Item item2 = new Item(2L, "华为手机", "手机",
                "华为", 23.00, "http://image.baidu.com/13123.jpg");
        //把商品添加到集合
        itemList.add(item2);


        //创建商品对象，给模拟数据
        Item item3 = new Item(3L, "oppo手机", "手机",
                "oppo", 56.00, "http://image.baidu.com/13123.jpg");
        //把商品添加到集合
        itemList.add(item3);

        //创建商品对象，给模拟数据
        Item item4 = new Item(4L, "vivo手机", "手机",
                "vivo", 109.00, "http://image.baidu.com/13123.jpg");
        //把商品添加到集合
        itemList.add(item4);



        //完成索引库添加
        repositoryDao.saveAll(itemList);
    }

    *//*

*/
/**
     * 需求：查询所有
     * 1，排序
     * 2，查询
     * 前提条件：由于查询数据要进行json格式相互转化。
     * 对象--->json
     * json--->对象
     * 1，对象需要序列化
     * 2，对象必须具有无参构造函数
     *//*
*/
/*

    @Test
    public void findAll(){
        //查询所有
        //Iterable<Item> list = repositoryDao.findAll();
        //排序查询
        Iterable<Item> list = repositoryDao.findAll(Sort.by("price").descending());
        //循环集合，获取对象
        for (Item item : list) {

            System.out.println("查询的结果是:"+item);

        }
    }


    *//*

*/
/**
     * 需求：查询价格在20 --- 60 块商品
     * 直接使用repositoryDao方法，无法实现复杂查询，因此必须定义自定义方法
     *
     *//*
*/
/*

    @Test
    public void findByPrice(){
        //调用接口方法
        List<Item> list = repositoryDao.findByPriceBetween(20, 60);
        //循环集合，获取对象
        for (Item item : list) {

            System.out.println("查询的结果是:"+item);

        }
    }

    *//*

*/
/**
     * 需求： 根据关键词查询商品
     * 条件： 必须使用自定义查询
     * 查询方式：matchQuery
     *
     * 查询步骤：
     * 1，创建查询构造器，构造查询对象
     * 2，添加查询条件
     * 3，查询
     *
     *//*
*/
/*

    @Test
    public void findByValue(){
        // 1，创建查询构造器，构造查询对象
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        //2，添加查询条件（查询对象）
        //QueryBuilders:查询条件构造的工具类，构造所有查询条件
        //参数1:域字段--- 根据这个字段去查询索引库
        //参数2：查询关键词
        searchQueryBuilder.withQuery(QueryBuilders.matchQuery("title","手机"));

        //3，查询
        Page<Item> page = repositoryDao.search(searchQueryBuilder.build());
        //获取查询总记录数
        long totalElements = page.getTotalElements();

        System.out.println("查询总记录数："+totalElements);

        //获取查询内容
        List<Item> list = page.getContent();

        //循环
        for (Item item : list) {

            System.out.println(item);
        }


    }
*//*


    */
/**
     * 需求：高亮查询
     * 高亮查询：就是把查询的关键词变成红色显示
     *//*

  */
/*  @Test
    public void findByHighLight(){

        // 1，创建查询构造器，构造查询对象
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        //2，添加查询条件（查询对象）
        //QueryBuilders:查询条件构造的工具类，构造所有查询条件
        //参数1:域字段--- 根据这个字段去查询索引库
        //参数2：查询关键词
        searchQueryBuilder.withQuery(QueryBuilders.matchQuery("specMap.网络","联通3G"));

        //3，设置高亮
        searchQueryBuilder.withHighlightFields(new HighlightBuilder
                            .Field("title") // 指定高亮域字段
                            .preTags("<font color='red'>") //指定高亮前缀
                            .postTags("</font>")); //指定高亮后缀

        //4，高亮查询
        Page<Item> page = esTemplate.queryForPage(searchQueryBuilder.build(),
                                                  Item.class,
                                                  new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response,
                                                    Class<T> aClass,
                                                    Pageable pageable) {

                //5,从response获取结果 ,命中的结果
                SearchHits hits = response.getHits();


                //定义一个集合，封装商品数据
                List<Item> itemList = new ArrayList<>();

                //6，获取命中文本结果
                //循环命中集合
                for (SearchHit hit : hits) {
                    //获取结果数据
                    Map<String, Object> result = hit.getSource();

                    //创建商品对象Item
                    Item item = new Item();

                    //1)获取id
                    Integer id = (Integer)result.get("id");

                    //判断id是否为空
                    if(id!=null){
                        item.setId((long)id);
                    }

                    //2) 获取title
                    HighlightField title = hit.getHighlightFields().get("title");
                    //判断
                    if(title!=null){
                        //title是高亮字段
                        //获取高亮值
                        String title1 = title.fragments()[0].toString();
                        //打印
                        System.out.println("高亮字段结果："+title1);
                        item.setTitle(title1);


                    }
                    //查询结果放入集合
                    itemList.add(item);
                }

                //7，返回值
                if(itemList!=null && itemList.size()>0){
                    return new AggregatedPageImpl(itemList);
                }


                return null;
            }
        });

    }
*//*



    */
/**
     * 需求：高亮查询
     * 高亮查询：就是把查询的关键词变成红色显示
     *//*

    @Test
    public void findByQuery(){

        // 1，创建查询构造器，构造查询对象
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title", "三星"))

                .filter(QueryBuilders.rangeQuery("price").from(10).to(4000))
                .filter(QueryBuilders.matchQuery("specMap.网络","3G"));

        //2，添加查询条件（查询对象）
        //QueryBuilders:查询条件构造的工具类，构造所有查询条件
        //参数1:域字段--- 根据这个字段去查询索引库
        //参数2：查询关键词
        searchQueryBuilder.withQuery(boolQueryBuilder);
        searchQueryBuilder.withPageable(PageRequest.of(0,100));

        //searchQueryBuilder.withFilter(new QueryStringQueryBuilder("xx").field("category"));

        //3,条件搜索
        //3-1: 分类
        //searchQueryBuilder.withFilter(QueryBuilders.termQuery("category","xx"));
        //3-2: 品牌
        //searchQueryBuilder.withFilter(QueryBuilders.termQuery("brand","小米"));
        //3-3：规格属性

        //3-4:价格区间过滤
        //searchQueryBuilder.withFilter(QueryBuilders.rangeQuery("price").from(3000).to(4000));

        //3-4：排序
        searchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        //3-5: 分页查询

        //3-6：设置高亮
        searchQueryBuilder.withHighlightFields(new HighlightBuilder
                .Field("title") // 指定高亮域字段
                .preTags("<font color='red'>") //指定高亮前缀
                .postTags("</font>")); //指定高亮后缀

        //4，高亮查询
        Page<EsData> page = esTemplate.queryForPage(searchQueryBuilder.build(),
                EsData.class,
                new SearchResultMapper() {
                    @Override
                    public <T> AggregatedPage<T> mapResults(SearchResponse response,
                                                            Class<T> aClass,
                                                            Pageable pageable) {

                        //5,从response获取结果 ,命中的结果
                        SearchHits hits = response.getHits();


                        //定义一个集合，封装商品数据
                        List<EsData> itemList = new ArrayList<>();

                        //6，获取命中文本结果
                        //循环命中集合
                        for (SearchHit hit : hits) {
                            //获取结果数据
                            Map<String, Object> result = hit.getSource();

                            //创建商品对象Item
                            EsData item = new EsData();

                            //1)获取id
                            Integer id = (Integer)result.get("id");

                            //判断id是否为空
                            if(id!=null){
                                item.setId((long)id);
                            }

                            //2) 获取title
                            HighlightField title = hit.getHighlightFields().get("title");
                            //判断
                            if(title!=null){
                                //title是高亮字段
                                //获取高亮值
                                String title1 = title.fragments()[0].toString();
                                //打印
                                System.out.println("高亮字段结果："+title1);
                                item.setTitle(title1);


                            }
                            //查询结果放入集合
                            itemList.add(item);
                        }

                        //7，返回值
                        if(itemList!=null && itemList.size()>0){
                            return new AggregatedPageImpl(itemList);
                        }


                        return null;
                    }
                });

        System.out.println(page);

    }

}


*/
