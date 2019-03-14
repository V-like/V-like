package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by on 2018/12/14.
 */
@Table(name = "tb_rec_floors")
public class RecFloors implements Serializable{

  /*  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
            `category_id` int(11) DEFAULT NULL COMMENT '外键',
            `floor_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '楼层名称',
            `floor_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '广告类型',
            `created` datetime DEFAULT NULL,
            `updated` datetime DEFAULT NULL,*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer category_id;
    private String floor_name;
    private String floor_type;
    private Date created;
    private Date updated;

    //定义集合，封装左侧选项卡的数据
    private List<RecSubCategorys> subCategorys;

    //定义集合，封装楼层选项卡
    private List<RecCategorys> recCategorys;

    //封装推荐品牌
    private List<RecBrand> brands;

    //封装推荐消息
    private List<RecNews> recNews;

    //封装边栏广告
    private RecAd leftAd;

    //右侧
    private RecAd rightAd;


    public List<RecSubCategorys> getSubCategorys() {
        return subCategorys;
    }

    public void setSubCategorys(List<RecSubCategorys> subCategorys) {
        this.subCategorys = subCategorys;
    }

    public List<RecCategorys> getRecCategorys() {
        return recCategorys;
    }

    public void setRecCategorys(List<RecCategorys> recCategorys) {
        this.recCategorys = recCategorys;
    }

    public List<RecBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<RecBrand> brands) {
        this.brands = brands;
    }

    public List<RecNews> getRecNews() {
        return recNews;
    }

    public void setRecNews(List<RecNews> recNews) {
        this.recNews = recNews;
    }

    public RecAd getLeftAd() {
        return leftAd;
    }

    public void setLeftAd(RecAd leftAd) {
        this.leftAd = leftAd;
    }

    public RecAd getRightAd() {
        return rightAd;
    }

    public void setRightAd(RecAd rightAd) {
        this.rightAd = rightAd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getFloor_name() {
        return floor_name;
    }

    public void setFloor_name(String floor_name) {
        this.floor_name = floor_name;
    }

    public String getFloor_type() {
        return floor_type;
    }

    public void setFloor_type(String floor_type) {
        this.floor_type = floor_type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
