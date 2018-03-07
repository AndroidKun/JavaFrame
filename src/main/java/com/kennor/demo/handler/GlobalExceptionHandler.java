package com.kennor.demo.handler;

import com.kennor.demo.common.Result;
import com.kennor.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局异常捕获
 */
@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Result processException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        logger.error("ERROR------->:",e);
        return ResultUtil.error(e.getMessage());
//        return ResultUtil.error("服务器繁忙，请稍候再试");
    }

}
