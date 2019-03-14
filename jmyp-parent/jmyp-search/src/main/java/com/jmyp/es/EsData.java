package com.jmyp.es;

import org.elasticsearch.index.settings.IndexDynamicSettings;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 2018/12/20.
 */
@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
public class EsData {

    //id
    @Id
    private Long id;

    @Field(type = FieldType.keyword)
    private Long spu_id;

    @Field(type = FieldType.keyword)
    private String category_name;
    @Field(type = FieldType.keyword)
    private String brand_name;

    //分类id
    @Field(type = FieldType.keyword)
    private Long category_id;

    //标题
    @Field(type = FieldType.text,analyzer = "ik_max_word")
    private String title;

    //买点
    @Field(type = FieldType.text,analyzer = "ik_max_word")
    private String sell_point;

    //价格
    //
    @Field(type = FieldType.keyword)
    private Float price;

    //图片
    @Field(type = FieldType.keyword)
    private String image;

    @Field(type = FieldType.keyword)
    private String spec;

    //p评论
    @Field(type = FieldType.keyword)
    private Integer comment_num;

    @IndexDynamicSettings
    private Map specMap = new HashMap();

    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public Map getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map specMap) {
        this.specMap = specMap;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }
}
