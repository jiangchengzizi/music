package com.music.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.dao.entity.TUser;
import com.music.service.service.TUserService;
import com.music.dao.mapper.TUserMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-11-16 15:36:53
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService{

    @Override
    public TUser getByUsername(String username) {
        return getOne(new QueryWrapper<TUser>().eq("username",username));
    }
}




