package com.syun.springboottest14.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description:
 * @program: springboottest14
 * @author: syun
 * @create: 2018-07-19 10:16
 */
@RestController
public class Hicontroller {


    @GetMapping("hi")
    public String HiController(){

        return "success";
    }

}
