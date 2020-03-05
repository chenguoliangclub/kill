package com.liang.kill.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
@Data
@Table(name = "tb_sku")
public class Sku {
    @Id
    private Long id;
    private Long spu_id;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String own_spec;
    private Integer enable;
    private Timestamp create_time;
    private Timestamp last_update_time;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Policy policy;
}
