package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by on 2019/1/3.
 */
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

/*    id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL COMMENT '商品id',
            `goods_id` bigint(20) DEFAULT NULL COMMENT 'SPU_ID',
            `order_id` bigint(20) NOT NULL COMMENT '订单id',
            `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
            `price` decimal(20,2) DEFAULT NULL COMMENT '商品单价',
            `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
            `total_fee` decimal(20,2) DEFAULT NULL COMMENT '商品总金额',
            `pic_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址'*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long item_id;
    private Long goods_id;
    private Long order_id;
    private String title;
    private Float price;
    private Integer num;
    private Float total_fee;
    private String pic_path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Float total_fee) {
        this.total_fee = total_fee;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }
}
