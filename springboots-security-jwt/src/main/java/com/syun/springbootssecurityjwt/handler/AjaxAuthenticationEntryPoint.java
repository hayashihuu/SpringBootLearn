package com.syun.springbootssecurityjwt.handler;


import com.google.gson.Gson;
import com.syun.springbootssecurityjwt.bean.AjaxResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setStatus("000");
        ajaxResponseBody.setMsg("need to login");
        Gson gson = new Gson();
        String s = gson.toJson(ajaxResponseBody);

        httpServletResponse.getWriter().write(s);
    }
}
