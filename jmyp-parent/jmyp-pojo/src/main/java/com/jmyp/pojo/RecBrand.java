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
@Table(name="tb_rec_brand")
public class RecBrand implements Serializable {

/*    id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_id` int(11) DEFAULT NULL,
  `rec_brand_name` varchar(50) DEFAULT NULL COMMENT '推荐品牌名称',
            `pic` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
            `updated` datetime DEFAULT NULL,*/
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private Integer floor_id;
private String rec_brand_name;
private String pic;
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

    public String getRec_brand_name() {
        return rec_brand_name;
    }

    public void setRec_brand_name(String rec_brand_name) {
        this.rec_brand_name = rec_brand_name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
