package com.music.dao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private ResultCode statusCode;

    // 手动传入参数 设置vo
    public AjaxResult(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    // 直接传入枚举类中的状态码加信息
    public AjaxResult(ResultCode statusCode) {
        this.statusCode = statusCode;
    }

    // 直接传入枚举类中的状态码加信息 加数据
    public AjaxResult(ResultCode statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }


    // 手动传入参数 设置vo
    public AjaxResult(int code, String message) {
        this.code = code;
        this.msg = message;
    }
}
