package com.springboot.demo.controller;

import com.springboot.demo.model.city;
import com.springboot.demo.service.cityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 表示此类下所有的方法都以json格式返回数值
 */
@RestController
public class testController {

    @Autowired
    cityService service;

    @RequestMapping("findbyIdtest")
    public city findByid(@RequestParam(value = "id",defaultValue = "1",required = false)Integer id){
        city temp = service.getCityInfoById(id);
        return temp;
    }

    @RequestMapping("/testHot")
    public String testHot(){
        return "hot success";
    }





}
