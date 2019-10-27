package com.freshjuice.fl.base.controller;

import com.freshjuice.fl.base.entity.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class FlExceptionHandler {


    /**
     * 参数校验异常(web)
     * */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult handlerValidateException(BindException e) {
        return JsonResult.buildFailedResult("-1", e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult handlerValidateException(MethodArgumentNotValidException  e) {
        return JsonResult.buildFailedResult("-1", e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 系统异常
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleOtherException(Exception e) {
        e.printStackTrace();
        log.error("otherException:" + e.getMessage(),e);
        if (e instanceof BindException) {
            BindException validException = (BindException)e;
            return JsonResult.buildFailedResult("-1", validException.getBindingResult().getFieldError().getDefaultMessage());
        }
        return JsonResult.buildFailedResult("-1", "系统异常");
    }

}
