package com.music.security.handler;



import com.music.dao.entity.TUser;
import com.music.service.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.security
 * @Author: 晚风
 * @CreateTime: 2022-10-17  18:41
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TUserService tUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser tUser = tUserService.getByUsername(username);
        if (tUser == null){
            throw new UsernameNotFoundException("用户名或者密码错误");
        }else if("1".equals(tUser.getStatus())){
            throw new UserCountLockException("该用户账号已被封禁，具体联系管理员！");
        }
        return new User(tUser.getUsername(),tUser.getPassword(),getUserAuthority(tUser.getId()));
    }

    public List<GrantedAuthority> getUserAuthority(Long userId) {

        // 格式ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,system:user:list,system:menu:query,system:menu:list,system:menu:add,system:user:delete,system:role:list,system:role:menu,system:user:edit,system:user:query,system:role:edit,system:user:add,system:user:role,system:menu:delete,system:role:add,system:role:query,system:menu:edit
//        String authority = tUserService.getUserAuthorityInfo(userId);
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);

        return new ArrayList<>();
    }
}
