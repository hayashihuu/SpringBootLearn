package com.syun.springbootssecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.syun.springbootssecurityjwt.bean.AjaxResponseBody;
import com.syun.springbootssecurityjwt.config.MyUserDetails;
import com.syun.springbootssecurityjwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sun.net.TelnetInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录成功返回生成jwt
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus("00");
        responseBody.setMsg("Login Success!");
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        //生成jwtToken设置启用时间为300秒
        String jwtToken = JwtTokenUtil.generateToken(myUserDetails.getUsername(), 100000);
        responseBody.setJwtToken(jwtToken);
        saveTokenToRedis(myUserDetails, jwtToken);
        response.getWriter().write(JSON.toJSONString(responseBody));
    }

    /**
     * 以username为key,jwtToken为值存入redis
     * @param token    jwt
     */
    public void saveTokenToRedis(MyUserDetails userDetails, String token) {

        Map<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        userDetails.getAuthorities().forEach(p->sb.append(p).append(","));
        map.put("token", token);
        map.put("authorities", sb.toString());
//        redisTemplate.opsForHash().putAll(userDetails.getUsername(), map);
        redisTemplate.opsForValue().set(userDetails.getUsername(), token, 5, TimeUnit.MINUTES);
    }
}
