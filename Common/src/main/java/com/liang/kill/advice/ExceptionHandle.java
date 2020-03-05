package com.liang.kill.advice;

import com.liang.kill.tools.ExceptionResult;
import com.liang.kill.tools.KillException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(KillException.class)
    public ResponseEntity<ExceptionResult> handleKillException(KillException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
