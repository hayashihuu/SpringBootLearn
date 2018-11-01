package com.syun.springbootssecurityjwt.mapper;

import com.syun.springbootssecurityjwt.bean.User;
import org.springframework.stereotype.Component;

/**
 * @description:  数据库查询模拟
 * @program: springboots-security-jwt
 * @author: syun
 * @create: 2018-11-01 14:56
 */
@Component
public class UserMapper {

    public User selectByUsername(String username ){
        User user = new User();
        user.setUsername(username);
        user.setPassword("1111");
        return user;
    }

    public User selectByMoblie(String moblie) {
        User user = new User();
        user.setUsername(moblie);   //用户名为手机号
        user.setPassword("1111");   //设置密码为验证码
        return user;
    }



}
