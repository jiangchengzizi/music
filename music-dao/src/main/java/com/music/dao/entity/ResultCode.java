package com.music.dao.entity;

import com.music.dao.entity.inter.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode implements StatusCode {
    CODE_SUCCESS(HttpStatus.OK.value(), "请求成功"),
    CODE_FAILED(HttpStatus.MULTIPLE_CHOICES.value(), "请求错误"),
    CODE_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "请求失败"),
    VALIDATE_ERROR(1001, "参数校验失败"),
    RESPONSE_PACK_ERROR(1002, "response返回包装失败");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
