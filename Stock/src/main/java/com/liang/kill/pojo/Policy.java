package com.liang.kill.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "tb_limit_policy")
public class Policy {
    @Id
    private Long id;
    @NotNull(message = "商品不能为空!")
    private Long sku_id;
    @Min(value = 1,message = "数量不能少于1")
    @NotNull(message = "数量不能为空!")
    private Long quanty;
    @Min(value = 1,message = "价格不能少于1")
    @NotNull(message = "价格不能为空!")
    private Long price;
    @NotEmpty(message = "开始时间不能为空!")
    private String begin_time;
    @NotEmpty(message = "结束时间不能为空!")
    private String end_time;
}
