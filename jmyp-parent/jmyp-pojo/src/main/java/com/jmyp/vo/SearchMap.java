package com.jmyp.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 2018/12/21.
 */
public class SearchMap implements Serializable{

   /* searchMap: {
        //根据关键词进行搜索
        keyword: sessionStorage.getItem("keyword"),
                //根据分类id进行搜索
                //list.html?cart_id=2
                cartId: location.search ? location.search.match(/cart_id=(\d+)/)[1] : '',
                //分类名称
                catgory_name: '',
                //品牌参数
                brand_id: 1,
                //规格列表
                spec_list: [],
        //每页显示的条数
        per_page: 30,
                //当前页
                page: 1,
                //价格
                price: '',
                //默认排序
                sort_by: 'xl',
                sort_way: 'desc',
                min_price: '',
                max_price: ''

    }*/

   //关键词
   private String keyword;
   //分类id
   private Long cat_id;
   //分类名称
   private String category_name;
   //品牌id
   private Long brand_id;
   //品牌名称
   private String brand_name;
   //分页，每页显示的条数
   private Integer per_page=30;
   //当前页
   private Integer page=0;

   private String sort_by;
   private String sort_way;
   private Float min_price;
   private Float max_price;

   //规格属性
   private Map<String,Object> specMap = new HashMap<>();


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getSort_way() {
        return sort_way;
    }

    public void setSort_way(String sort_way) {
        this.sort_way = sort_way;
    }

    public Float getMin_price() {
        return min_price;
    }

    public void setMin_price(Float min_price) {
        this.min_price = min_price;
    }

    public Float getMax_price() {
        return max_price;
    }

    public void setMax_price(Float max_price) {
        this.max_price = max_price;
    }

    public Map<String, Object> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map<String, Object> specMap) {
        this.specMap = specMap;
    }
}
