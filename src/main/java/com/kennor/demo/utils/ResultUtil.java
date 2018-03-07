package com.kennor.demo.utils;

import com.kennor.demo.common.Result;

public class ResultUtil {

    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        result.setData(obj);
        return result;
    }

    public static Result error(String errorMsg){
        Result result = new Result();
        result.setCode(-1);
        result.setMsg(errorMsg);
        return result;
    }
}
