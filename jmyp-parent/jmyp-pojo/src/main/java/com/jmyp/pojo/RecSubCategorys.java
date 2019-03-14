package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2018/12/14.
 */
@Table(name = "tb_rec_sub_categorys")
public class RecSubCategorys implements Serializable{

   /* id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
            `floor_id` int(11) DEFAULT NULL COMMENT '外键',
            `sub_name` varchar(50) DEFAULT NULL COMMENT '分类标签名称',
            `link` varchar(50) DEFAULT NULL COMMENT '分类标签连接地址',
            `created` datetime DEFAULT NULL,
            `updated` datetime DEFAULT NULL,*/
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private Integer floor_id;
   private String sub_name;
   private String link;
   private Date created;
   private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
