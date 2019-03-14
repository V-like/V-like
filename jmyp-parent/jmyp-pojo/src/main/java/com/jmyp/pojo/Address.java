package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by on 2019/1/3.
 */
@Table(name = "tb_address")
public class Address implements Serializable {

   /* id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
            `shr_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人姓名',
            `shr_mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人手机',
            `shr_province` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人省份',
            `shr_city` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人城市',
            `shr_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人地区',
            `shr_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '收货人详情地址',
            `isdefault` int(11) NOT NULL DEFAULT '0' COMMENT '1:默认;0:不是'*/

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private Integer user_id;
   private String shr_name;
   private String shr_mobile;
   private String shr_province;
   private String shr_city;
   private String shr_area;
   private String shr_address;
   private Integer isdefault;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getShr_name() {
        return shr_name;
    }

    public void setShr_name(String shr_name) {
        this.shr_name = shr_name;
    }

    public String getShr_mobile() {
        return shr_mobile;
    }

    public void setShr_mobile(String shr_mobile) {
        this.shr_mobile = shr_mobile;
    }

    public String getShr_province() {
        return shr_province;
    }

    public void setShr_province(String shr_province) {
        this.shr_province = shr_province;
    }

    public String getShr_city() {
        return shr_city;
    }

    public void setShr_city(String shr_city) {
        this.shr_city = shr_city;
    }

    public String getShr_area() {
        return shr_area;
    }

    public void setShr_area(String shr_area) {
        this.shr_area = shr_area;
    }

    public String getShr_address() {
        return shr_address;
    }

    public void setShr_address(String shr_address) {
        this.shr_address = shr_address;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}
