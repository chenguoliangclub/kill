package com.liang.kill.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KillException extends RuntimeException{
    private KillExceptionEnum exceptionEnum;
}
