package com.liang.kill.tools;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private T data;
    private String msg;
}
