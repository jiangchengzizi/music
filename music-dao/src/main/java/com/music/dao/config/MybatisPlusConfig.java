package com.music.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: Music_back
 * @BelongsPackage: com.music.dao.config
 * @Author: 晚风
 * @CreateTime: 2022-11-15  22:40
 * @Description: TODO
 * @Version: 1.0
 */

@Configuration
@MapperScan("com.music.dao")
public class MybatisPlusConfig {


}
