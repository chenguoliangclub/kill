package com.liang.kill.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  KillExceptionEnum {
    SPU_NOT_FOUND(404,"商品未查到"),
    SKU_NOT_FOUND(404,"商品未查到"),
    POLICY_INSERT_ERROR(400,"秒杀政策添加失败"),
    POLICY_NOT_FOUND(400,"秒杀政策不存在"),
    STORAGE_INSERT_ERROR(400,"商品库存添加失败"),
    STORAGE_HISTORY_INSERT_ERROR(400,"商品库存记录添加失败"),
    STORAGE_UPDATE_ERROR(400,"商品库存更新失败"),
    STOCK_NOT_ENOUGH(400,"商品库存不足")
    ;
    private int code;
    private String msg;
}
