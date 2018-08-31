package com.syun.springbootsecurity.service;

import com.syun.springbootsecurity.dto.User;

/*
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-11 19:14
 */

public interface IUserService {

    /**
     * 根据Id查询用户对象  一对一
     * @param id 编号
     * @return 用户对象
     */
    User getUserById(Integer id);



}
