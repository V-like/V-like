package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by on 2018/12/25.
 */
@Table(name = "tb_sku_photo")
public class SkuPhoto implements Serializable {

 /*   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sku_id` int(10) unsigned NOT NULL COMMENT 'SPUID',
            `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '原图路径*/

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 private Integer sku_id;
 private String url;
 private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
