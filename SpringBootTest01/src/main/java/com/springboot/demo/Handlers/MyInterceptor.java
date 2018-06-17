package com.springboot.demo.Handlers;

import org.apache.catalina.User;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置拦截器
 */
public class MyInterceptor implements HandlerInterceptor {


//    controller处理前
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            boolean flag=true;
//        User user = (User) request.getSession().getAttribute("user");
//        if(null==user){
//            response.sendRedirect("toLogin");
//            flag = false;
//        }else {
//            flag = true;
//        }

            return flag;
    }









    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
