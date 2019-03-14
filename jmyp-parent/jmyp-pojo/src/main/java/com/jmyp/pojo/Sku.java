package com.jmyp.pojo;

import org.omg.CORBA.INTERNAL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2018/12/25.
 */
@Table(name = "tb_sku")
public class Sku implements Serializable {
 /*
    `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'sku id',
            `stock` int(11) DEFAULT NULL,
  `spu_id` int(20) NOT NULL COMMENT 'spu id',
            `sku_name` varchar(255) NOT NULL COMMENT '商品标题',
            `images` varchar(1000) DEFAULT '' COMMENT '商品的图片，多个图片以‘,’分割',
            `price` double(15,0) NOT NULL DEFAULT '0' COMMENT '销售价格，单位为分',
            `spec_list` varchar(200) DEFAULT NULL,
  `spec_list_code` varchar(1000) DEFAULT '' COMMENT 'sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序',
            `created_at` datetime NOT NULL COMMENT '添加时间',
            `updated_at` datetime NOT NULL COMMENT '最后修改时间',*/

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private Integer stock;
   private Integer spu_id;
   private String sku_name;
   private String images;
   private Double price;
   private String spec_list;
   private String spec_list_code;
   private Date created_at;
   private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Integer spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(String spec_list) {
        this.spec_list = spec_list;
    }

    public String getSpec_list_code() {
        return spec_list_code;
    }

    public void setSpec_list_code(String spec_list_code) {
        this.spec_list_code = spec_list_code;
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
