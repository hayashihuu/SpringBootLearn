package com.syun.springboottest12.service;

import com.syun.springboottest12.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-11 19:14
 */
@Service
public interface IUserService {

    /**
     * 根据Id查询用户对象  一对一
     * @param id 编号
     * @return 用户对象
     */
    List<User> getUserById(Integer id);

    /**
     * 根据用户名查询用户对象 一对多
     * @return  List
     */
    List<User> queryUserList();



}
