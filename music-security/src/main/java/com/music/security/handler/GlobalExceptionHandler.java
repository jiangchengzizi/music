package com.music.security.handler;

import com.music.dao.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.common.exception
 * @Author: 晚风
 * @CreateTime: 2022-10-17  18:56
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e){
        log.error("运行时异常：--------{}",e.getMessage());
        System.out.println("运行时异常:");
        return R.error(e.getMessage());
    }

}
