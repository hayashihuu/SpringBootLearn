package com.syun.springbootsecurity.service;

import com.syun.springbootsecurity.domain.Permission;
import com.syun.springbootsecurity.domain.SysUser;
import com.syun.springbootsecurity.mapper.PermissionDao;
import com.syun.springbootsecurity.mapper.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @program: springboot-security
 * @author: syun
 * @create: 2018-08-29 09:25
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserDao userDao;

    private final PermissionDao permissionDao;


    @Autowired
    public MyUserDetailsService(UserDao userDao, PermissionDao permissionDao) {
        this.userDao = userDao;
        this.permissionDao = permissionDao;
    }


    public UserDetails loadUserByUsername(String username) {
        SysUser user = userDao.findByUserName(username);

        if (user != null) {
            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
//            List<Permission> permissions = new ArrayList<>();
//            Permission permission = new Permission();
//            permission.setName("admin");
//            permissions.add(permission);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            String pwd = user.getPassword();
            return new User(user.getUsername(), pwd, grantedAuthorities);
        } else {
            logger.info("用户未找到: {}", username);
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
