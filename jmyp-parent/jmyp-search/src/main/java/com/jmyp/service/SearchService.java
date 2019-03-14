package com.jmyp.service;

import com.jmyp.es.EsData;
import com.jmyp.mapper.BrandMapper;
import com.jmyp.mapper.SpecificationMapper;
import com.jmyp.mapper.SpecificationOptionMapper;
import com.jmyp.pojo.Brand;
import com.jmyp.pojo.Specification;
import com.jmyp.pojo.SpecificationOption;
import com.jmyp.vo.BaseResult;
import com.jmyp.vo.SearchMap;
import com.netflix.discovery.converters.Auto;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by on 2018/12/21.
 */
@Service
public class SearchService {

    //注入搜索模板对象
    @Autowired
    private ElasticsearchTemplate esTemplate;

    //注入mapper接口代理对象
    @Autowired
    private BrandMapper brandMapper;

    //注入规格mapper
    @Autowired
    private SpecificationMapper specificationMapper;

    //注入规格选项mapper
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;


    /**
     * 需求：根据分类id查询品牌数据
     * 返回值：map
     * map封装集合：
     * 1，品牌集合
     * 2，规格集合
     */
    public Map findBrandAndSpecList(Long cat_id){

        //创建map对象，封装品牌，规格集合数据
        Map maps = new HashMap();

        //查询品牌数据
        List<Brand> brandList = brandMapper.findBrandListByCategoryId(cat_id);
        //封装品牌数据
        maps.put("brand_list",brandList);


        //查询规格及规格属性值
        //创建example对象
        Example example = new Example(Specification.class);
        //创建criteria对象
        Example.Criteria criteria = example.createCriteria();
        //设置查询参数
        criteria.andEqualTo("category_id",cat_id);
        //执行查询
        List<Specification> specificationList = specificationMapper.selectByExample(example);

        //循环规格，查询规格选项
        for (Specification specification : specificationList) {
            //创建example对象
            Example example1 = new Example(SpecificationOption.class);
            //创建criteria对象
            Example.Criteria criteria1 = example1.createCriteria();
            //设置查询参数
            criteria1.andEqualTo("spec_id",specification.getId());

            //执行查询
            List<SpecificationOption> specificationOptionList = specificationOptionMapper.selectByExample(example1);

            //把规格选项添加到规格对象中
            specification.setOptionList(specificationOptionList);

        }


        //把规格及规格选项的值放入map
        maps.put("spec_list",specificationList);

        return  maps;



    }





    /**
     * 需求：根据分类id，品牌，价格区间，规格属性，分页
     * 请求：searchList
     * 参数：
     * SearchMap searchMap
     * 返回值：
     * BaseResult
     * 方法：searchList
     * 业务流程：
     * 1，根据分类id检索索引库
     * 2，分页查询
     */
    public BaseResult searchList(SearchMap searchMap) {

        try {
            // 1，创建查询构造器，构造查询对象
            NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();

             BoolQueryBuilder boolQueryBuilder = null;

                /*   .filter(QueryBuilders.rangeQuery("price").from(10).to(4000))
                .filter(QueryBuilders.matchQuery("specMap.网络","3G"));*/

            //获取查询参数

            //1,根据分类id查询
            Long cat_id = searchMap.getCat_id();
            //判断参数是否为空
            if (cat_id != null) {
                //构造查询条件
                boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("category_id", cat_id));
                //searchQueryBuilder.withQuery(QueryBuilders.matchQuery("category_id", cat_id));
                //boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(10).to(4000));
                //boolQueryBuilder.filter(QueryBuilders.termQuery("specMap.网络","联通3G"));


            } else {
                //查询所有
                boolQueryBuilder.must(QueryBuilders.matchAllQuery());
            }


            //2,根据品牌查询
            //获取品牌参数
            String brand_name = searchMap.getBrand_name();
            //判断此参数是否为空
            if(brand_name!=null && !"".equals(brand_name)){
                boolQueryBuilder.filter(QueryBuilders.termQuery("brand_name",brand_name));
            }

            //3，价格过滤查询
            //获取最小价格区间
            Float min_price = searchMap.getMin_price();
            //获取最大价格区间
            Float max_price = searchMap.getMax_price();

            //判断
            if(min_price!=null && max_price!=null){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(min_price).to(max_price));
            }

            //4,规格属性过滤查询
            //获取规格属性对象
            Map<String, Object> specMap = searchMap.getSpecMap();

            //判断参数是否存在
            if(specMap!=null){

                //参数格式：{“颜色”:"白色","内存"：“4G”}
                for(String key : specMap.keySet()){
                    //规格搜索条件组装
                    boolQueryBuilder.filter(QueryBuilders.termQuery("specMap."+key+".keyword",specMap.get(key)));

                }

            }


            //把所有的条件放入searchQueryBuilder
            searchQueryBuilder.withQuery(boolQueryBuilder);

           //2，根据价格排序,
            //从searchMap排序方式，排序字段
            //获取排序字段: price
            String sort_by = searchMap.getSort_by();
            //获取排序方式： asc desc
            String sort_way = searchMap.getSort_way();

            //判断排序条件参数是否为空
            if(sort_by!=null && !"".equals(sort_by)){
                //判断
                if(sort_way.equals("asc")){
                    searchQueryBuilder.withSort(SortBuilders.fieldSort(sort_by).order(SortOrder.ASC));
                }else{
                    searchQueryBuilder.withSort(SortBuilders.fieldSort(sort_by).order(SortOrder.DESC));
                }

            }


            //分页
            //获取分页参数
            Integer per_page = searchMap.getPer_page();
            //获取当前页码
            Integer page = searchMap.getPage();

            //判断
            if (page == null) {
                page = 0;
            }
            //判断
            if (per_page == null) {
                per_page = 30;
            }

            //定义总记录数
            final long[] totalHits = {0};

            //设置分页参数
            searchQueryBuilder.withPageable(PageRequest.of(page, per_page));


            //执行查询
            Page<EsData> esPage = esTemplate.queryForPage(searchQueryBuilder.build(), EsData.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {

                    //从response获取命中的结果
                    SearchHits hits = response.getHits();

                    //获取查询总条数
                    totalHits[0] = hits.getTotalHits();

                    //定义一个集合，封装商品数据
                    List<EsData> itemList = new ArrayList<>();

                    //6，获取命中文本结果
                    //循环命中集合
                    for (SearchHit hit : hits) {
                        //获取结果数据
                        Map<String, Object> result = hit.getSource();

                        //创建商品对象Item
                        EsData esData = new EsData();

                        //1)获取id
                        Integer id = (Integer) result.get("id");

                        //判断id是否为空
                        if (id != null) {
                            esData.setId((long) id);
                        }

                        //2) 获取title
                        HighlightField title = hit.getHighlightFields().get("title");
                        //判断
                        if (title != null) {
                            //title是高亮字段
                            //获取高亮值
                            String title1 = title.fragments()[0].toString();
                            //打印
                            System.out.println("高亮字段结果：" + title1);
                            esData.setTitle(title1);


                        } else {
                            //从索引库获取标题
                            String title1 = (String) result.get("title");
                            esData.setTitle(title1);

                        }

                        //获取价格
                        Double price = (Double) result.get("price");
                        //判断价格值是否存在
                        if (price != null) {
                            esData.setPrice(price.floatValue());
                        }

                        //获取spuid
                        Integer spu_id = (Integer) result.get("spu_id");

                        //判断是否为空
                        if(spu_id!=null){
                            esData.setSpu_id(spu_id.longValue());
                        }

                        //获取图片
                        String image = (String) result.get("image");
                        if (image != null) {
                            esData.setImage(image);
                        }

                        //查询结果放入集合
                        itemList.add(esData);
                    }

                    //7，返回值
                    if (itemList != null && itemList.size() > 0) {
                        return new AggregatedPageImpl(itemList);
                    }


                    return null;
                }

            });

            //查询成功
            return new BaseResult(0,"查询成功", totalHits[0],esPage.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败
            return new BaseResult(1,"查询失败",null,null);
        }


    }


}
