package com.music.security.handler;

import java.util.UUID;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.util
 * @Author: 晚风
 * @CreateTime: 2022-11-13  13:23
 * @Description: TODO
 * @Version: 1.0
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
