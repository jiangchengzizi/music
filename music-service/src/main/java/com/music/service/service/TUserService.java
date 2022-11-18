package com.music.service.service;

import com.music.dao.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ASUS
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-11-16 15:36:53
*/

public interface TUserService extends IService<TUser> {

    TUser getByUsername(String username);

}
