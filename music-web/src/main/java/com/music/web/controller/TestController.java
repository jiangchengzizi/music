package com.music.web.controller;

import com.music.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: Music_back
 * @BelongsPackage: com.music.web.controller
 * @Author: 晚风
 * @CreateTime: 2022-11-15  22:42
 * @Description: TODO
 * @Version: 1.0
 */

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestMapper testMapper;
}
