package com.syun.springbootssecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.syun.springbootssecurityjwt.bean.AjaxResponseBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//细粒度 admin user 权限问题

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("03");
        responseBody.setMsg("Need Authorities!");

        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
