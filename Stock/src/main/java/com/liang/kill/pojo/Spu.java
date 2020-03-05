package com.liang.kill.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table(name = "tb_spu")
public class Spu {
    @Id
    private Long id;
    private String title;
    private String sub_title;
    private int cid1;
    private int cid2;
    private int cid3;
    private int brand_id;
    private Timestamp create_time;
    private Timestamp last_update_time;
}
