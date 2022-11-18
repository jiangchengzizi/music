package com.music.security.handler;


import org.springframework.security.core.AuthenticationException;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.common.exception
 * @Author: 晚风
 * @CreateTime: 2022-10-17  18:53
 * @Description: TODO
 * @Version: 1.0
 */
public class UserCountLockException extends AuthenticationException {
    public UserCountLockException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserCountLockException(String msg) {
        super(msg);
    }
}
