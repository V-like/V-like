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
@Table(name="tb_rec_categorys")
public class RecCategorys implements Serializable {

 /*   `id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_id` int(11) DEFAULT NULL COMMENT '外键',
            `cat_name` varchar(50) DEFAULT NULL COMMENT '楼层分类选项卡名称',
            `created` datetime DEFAULT NULL,
            `updated` datetime DEFAULT NULL*/
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 private Integer floor_id;
 private String cat_name;
 private Date created;
 private Date updated;


 //定义集合，封装楼层选项卡下面对应商品
    private List<RecGoods> goods;

    public List<RecGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<RecGoods> goods) {
        this.goods = goods;
    }

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

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
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
