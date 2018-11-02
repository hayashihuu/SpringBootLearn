package com.syun.springbootssecurityjwt.config;

import com.syun.springbootssecurityjwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String TYPE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //请求头为 Authorization
        //请求体为 Bearer token

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            final String authToken = authHeader.substring("Bearer ".length());
            String username = JwtTokenUtil.parseToken(authToken);
//            两种判断token过期方式
            if (!isAlive(authToken)) {
                log.info("token过期,username: {}", username);
            }

            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String token = ops.get(username);
            if (token == null) {
                log.info("token过期,username: {}", username);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                获取用户身份
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 判断jwtToken是否已经过期
     *
     * @param jwtToken jwt
     * @return 验证结果
     */
    public static boolean isAlive(String jwtToken) {

        if (JwtTokenUtil.getDate(jwtToken).getTime() > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }


}
