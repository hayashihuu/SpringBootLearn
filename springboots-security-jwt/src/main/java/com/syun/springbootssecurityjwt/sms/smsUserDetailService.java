package com.syun.springbootssecurityjwt.sms;

import com.syun.springbootssecurityjwt.bean.User;
import com.syun.springbootssecurityjwt.config.MyUserDetails;
import com.syun.springbootssecurityjwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @description:
 * @program: springboots-security-jwt
 * @author: syun
 * @create: 2018-11-01 14:53
 */
@Service
public class smsUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        MyUserDetails myUserDetails = new MyUserDetails();
        User user = mapper.selectByMoblie(s);

        if (user == null) {
            throw new UsernameNotFoundException("此用户不存在!");
        }

        myUserDetails.setUsername(user.getUsername());
        myUserDetails.setPassword(user.getPassword());

        //模拟从数据库取出的权限
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        //  set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        set.add(new SimpleGrantedAuthority("ROLE_USER"));
        myUserDetails.setAuthorities(set);
        return myUserDetails;

    }
}
