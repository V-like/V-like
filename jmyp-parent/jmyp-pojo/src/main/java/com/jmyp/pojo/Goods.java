package com.jmyp.pojo;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by on 2018/12/25.
 */
@Table(name = "tb_goods")
public class Goods implements Serializable {

    private Long id;
    private String goods_name;
    private Long default_item_id;
    private String audit_status;
    private String is_marketable;
    private Long  brand_id;
    private String caption;
    private Long category1_id;
    private Long category2_id;
    private Long category3_id;
    private String small_pic;
    private double price;
    private Long type_template_id;
    private String is_enable_spec;
    private String is_delete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Long getDefault_item_id() {
        return default_item_id;
    }

    public void setDefault_item_id(Long default_item_id) {
        this.default_item_id = default_item_id;
    }

    public String getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }

    public String getIs_marketable() {
        return is_marketable;
    }

    public void setIs_marketable(String is_marketable) {
        this.is_marketable = is_marketable;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getCategory1_id() {
        return category1_id;
    }

    public void setCategory1_id(Long category1_id) {
        this.category1_id = category1_id;
    }

    public Long getCategory2_id() {
        return category2_id;
    }

    public void setCategory2_id(Long category2_id) {
        this.category2_id = category2_id;
    }

    public Long getCategory3_id() {
        return category3_id;
    }

    public void setCategory3_id(Long category3_id) {
        this.category3_id = category3_id;
    }

    public String getSmall_pic() {
        return small_pic;
    }

    public void setSmall_pic(String small_pic) {
        this.small_pic = small_pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getType_template_id() {
        return type_template_id;
    }

    public void setType_template_id(Long type_template_id) {
        this.type_template_id = type_template_id;
    }

    public String getIs_enable_spec() {
        return is_enable_spec;
    }

    public void setIs_enable_spec(String is_enable_spec) {
        this.is_enable_spec = is_enable_spec;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
