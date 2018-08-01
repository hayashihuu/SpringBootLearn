package com.syun.springboottest15.controller;


import com.syun.springboottest15.dto.User;
import com.syun.springboottest15.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @description: 测试缓存
 * @program: springboottest15
 * @author: syun
 * @create: 2018-07-11 13:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloController {

    @Autowired
    private UserMapper mapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }


    @Test
    public void test01(){
        User u1 = mapper.selectByPrimaryKey(1);
        System.out.println("第一次查询: " + u1.getUsername());

        User u2 = mapper.selectByPrimaryKey(1);
        System.out.println("第二次查询: " + u2.getUsername());

    }



}
