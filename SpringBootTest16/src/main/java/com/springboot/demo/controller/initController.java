package com.springboot.demo.controller;

import com.springboot.demo.model.city;
import com.springboot.demo.service.cityService;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class initController {


    @Autowired
    cityService service;

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping("findbyId")
    @ResponseBody
    public city findByid(@RequestParam(value = "id",defaultValue = "1",required = false)Integer id){
        city temp = service.getCityInfoById(id);
        return temp;
    }



    /**
     *登录操作,举例
     **/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
            User user =new User();
            request.getSession().setAttribute("user",user);
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }




}
