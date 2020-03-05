package com.liang.kill.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spu_id;
    private String description;
    private String generic_spec;
    private String special_spec;
    private String packing_list;
    private String after_service;
}
