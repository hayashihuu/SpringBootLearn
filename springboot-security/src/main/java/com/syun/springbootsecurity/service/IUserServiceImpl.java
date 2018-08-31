package com.syun.springbootsecurity.service;

import com.syun.springbootsecurity.dto.User;
import com.syun.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @description:
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-11 19:15
 */

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    UserMapper mapper;


    /**
     * 表一对一查询
     * @param id 编号
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }



}
