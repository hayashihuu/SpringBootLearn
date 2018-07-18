package com.syun.springboottest12.service.serviceImpl;

import com.syun.springboottest12.dto.User;
import com.syun.springboottest12.mapper.IUserMapper;
import com.syun.springboottest12.service.IUserService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

/*
 * @description:
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-11 19:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    IUserMapper mapper;


    /**
     * 表一对一查询
     * @param id 编号
     * @return
     */
    @Override
    public List<User> getUserById(Integer id) {

        return mapper.queryUserRole(id);
    }


    /**
     * 表一对多查询
     * @return
     */
    @Override
    public List<User> queryUserList() {
        List<User> list = mapper.queryForList();
        System.out.println(list.toString());
        return list;
    }

    /**
     * 测试多表联查
     */
    @Test
    public void test(){
//        queryUserList();
        System.out.println(getUserById(1).toString());
    }
}