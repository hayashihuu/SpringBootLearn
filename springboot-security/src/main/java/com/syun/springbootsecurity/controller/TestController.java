package com.syun.springbootsecurity.controller;

import com.syun.springbootsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description:
 * @program: springboot-security
 * @author: syun
 * @create: 2018-08-28 15:38
 */
@RestController
public class TestController {


    @Autowired
    private IUserService service;

    @RequestMapping("/test")
    private String test() {

        System.out.println(service.getUserById(1).toString());
        return "success";
    }

}
