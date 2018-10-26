package com.syun.springbootssecurityjwt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String toLogin(){
        return "/login";
    }

    @ResponseBody
    @RequestMapping("/sys")
    public String getSys(){
        return "systemManager";
    }

    @RequestMapping(value = "/test/hw",method = RequestMethod.GET)
    @ResponseBody
    public String testHelloWorld(){
        return "hello world";
    }

    @RequestMapping("/test/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = "/sys/sys", method = RequestMethod.GET)
    @ResponseBody
    public String toSys() {
        return "sys success";
    }
}
