package com.liang.kill.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_stock_storage_history")
public class StorageHistory {
    @Id
    private Long id;
    private Long stock_storage_id;
    private Long in_quanty;
    private Long out_quanty;
}
