package com.liang.kill.tools;

import lombok.Getter;

@Getter
public class ExceptionResult {
    private int code;
    private String msg;
    public ExceptionResult(KillExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
}
