package com.syun.springboottest12.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @description:
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-11 13:49
 */
@Controller
public class HelloController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }



}
