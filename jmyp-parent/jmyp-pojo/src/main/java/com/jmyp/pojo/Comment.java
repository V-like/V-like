package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2018/12/25.
 */
@Table(name = "tb_sku_comment")
public class Comment implements Serializable {

  /*  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
            `spu_id` int(10) unsigned NOT NULL COMMENT 'SPUID',
            `sku_id` int(10) unsigned NOT NULL COMMENT 'SKUID',
            `ratio` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '0好评 1  中评   2 差评 ',
            `spec_list` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '规格列表',
            `content` varchar(600) COLLATE utf8_unicode_ci NOT NULL COMMENT '评论内容，最大600个字符',
            `star` int(3) unsigned NOT NULL COMMENT '打分，1~5分',
            `isshow` int(11) NOT NULL DEFAULT '0' COMMENT '0 显示 1 不显示',
            `sn` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date created_at;
    private Date updated_at;
    private Integer user_id;
    private Integer spu_id;
    private Integer sku_id;
    private String ratio;
    private String spec_list;
    private String content;
    private Integer star;
    private Integer isshow;
    private String sn;

    //一条评论只属于一个用户
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Integer spu_id) {
        this.spu_id = spu_id;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(String spec_list) {
        this.spec_list = spec_list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
