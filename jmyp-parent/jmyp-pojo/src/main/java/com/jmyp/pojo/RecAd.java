package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by on 2018/12/14.
 */
@Table(name = "tb_rec_ad")
public class RecAd implements Serializable{
 /*
    `id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_id` int(11) DEFAULT NULL COMMENT '外键',
            `title` varchar(50) DEFAULT NULL COMMENT '广告标题-边栏广告、楼层广告',
            `url` varchar(50) DEFAULT NULL COMMENT '连接',
            `pic` varchar(100) DEFAULT NULL COMMENT '图片',
            `status` int(11) DEFAULT NULL COMMENT '状态',
            `sort_order` int(11) DEFAULT NULL COMMENT '排序',
            `type` int(11) DEFAULT NULL COMMENT '广告类型 01，右侧边栏广告 2、左侧边栏广告',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8*/

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 private Integer floor_id;
 private String title;
 private String url;
 private String pic;
 private Integer status;
 private Integer sort_order;
 private Integer type;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
