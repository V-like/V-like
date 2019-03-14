package com.jmyp.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2018/12/25.
 */
@Table(name = "tb_spu")
public class Spu implements Serializable {

  /*  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'spu id',
            `spu_name` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
            `spu_subname` varchar(255) DEFAULT '' COMMENT '子标题',
            `logo` varchar(255) DEFAULT NULL,
  `cat1_id` int(20) NOT NULL COMMENT '1级类目id',
            `cat2_id` int(20) NOT NULL COMMENT '2级类目id',
            `cat3_id` int(20) NOT NULL COMMENT '3级类目id',
            `brand_id` int(20) NOT NULL COMMENT '商品所属品牌id',
            `check_time` datetime DEFAULT NULL COMMENT '审核时间',
            `check_status` int(11) DEFAULT NULL COMMENT '审核状态，0：未审核，1：已通过，2：未通过',
            `price` double DEFAULT NULL COMMENT '商品价格，单位：元',
            `is_on_sale` int(11) DEFAULT NULL COMMENT '是否上架 0 未上架 1 上架',
            `on_sale_time` datetime DEFAULT NULL COMMENT '上架时间',
            `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
            `weight` double DEFAULT NULL COMMENT '重量',
            `description` text COMMENT '商品详情',
            `packages` text COMMENT '包装',
            `aftersale` longtext COMMENT '售后',
            `spec_list` longtext COMMENT '规格列表',
            `created_at` datetime DEFAULT NULL COMMENT '添加时间',
            `updated_at` datetime DEFAULT NULL COMMENT '最后修改时间',*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //默认sku
    private Integer sku_id;

    private String spu_name;
    private String spu_subname;
    private String logo;
    private Integer cat1_id;
    private Integer cat2_id;
    private Integer cat3_id;
    private Integer brand_id;
    private Date check_time;
    private Integer check_status;
    private Double price;
    private Integer is_on_sale;
    private Date on_sale_time;
    private Date deleted_at;
    private Double weight;
    private String description;
    private String packages;
    private String aftersale;
    private String spec_list;
    private Date created_at;
    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_subname() {
        return spu_subname;
    }

    public void setSpu_subname(String spu_subname) {
        this.spu_subname = spu_subname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getCat1_id() {
        return cat1_id;
    }

    public void setCat1_id(Integer cat1_id) {
        this.cat1_id = cat1_id;
    }

    public Integer getCat2_id() {
        return cat2_id;
    }

    public void setCat2_id(Integer cat2_id) {
        this.cat2_id = cat2_id;
    }

    public Integer getCat3_id() {
        return cat3_id;
    }

    public void setCat3_id(Integer cat3_id) {
        this.cat3_id = cat3_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Date getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Date check_time) {
        this.check_time = check_time;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(Integer is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public Date getOn_sale_time() {
        return on_sale_time;
    }

    public void setOn_sale_time(Date on_sale_time) {
        this.on_sale_time = on_sale_time;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getAftersale() {
        return aftersale;
    }

    public void setAftersale(String aftersale) {
        this.aftersale = aftersale;
    }

    public String getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(String spec_list) {
        this.spec_list = spec_list;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
