package com.liang.kill.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Table(name = "tb_stock_storage")
public class Storage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long warehouse_id;
    private Long sku_id;
    private Long quanty;
}
