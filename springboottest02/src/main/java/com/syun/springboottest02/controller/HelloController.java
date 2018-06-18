package com.syun.springboottest02.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello spring boot";
    }

    /**
     * 测试配置文件的读取
     */
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;
    @RequestMapping("/miya")
    public String miya(){
        return "name: " + name + "age: " + age;
    }

//
//    @Test
//    public void test(){
//        System.out.println("test sucess");
//    }


}
