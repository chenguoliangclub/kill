package com.liang.kill.pojo;

import lombok.Data;

import javax.persistence.Table;
@Data
@Table(name = "tb_order")
public class Order {
    private Long order_id;
    private Long total_fee;
    private Long actual_fee;
    private Long post_fee;
    private Integer payment_type;
    private Long user_id;
    private Integer status;
    private String create_time;
    private Long sku_id;
    private Integer num;
    private String title;
    private String own_spec;
    private Long price;
    private String image;
}
