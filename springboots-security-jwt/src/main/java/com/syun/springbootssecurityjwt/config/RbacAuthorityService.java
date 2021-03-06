package com.syun.springbootssecurityjwt.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component("rbacauthorityservice")
public class RbacAuthorityService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        //return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
        //return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
        //得到的principal的信息是用户名还是整个用户信息取决于在SelfAuthenticationProvider中传参的方式
        Object userInfo = authentication.getPrincipal();

        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {

            String username = ((UserDetails) userInfo).getUsername();

            Collection<? extends GrantedAuthority> authorities = ((UserDetails) userInfo).getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                    //admin 可以访问的资源
                    Set<String> urls = new HashSet();
//                    添加permission
                    urls.add("/sys/**");
                    urls.add("/test/**");
                    AntPathMatcher antPathMatcher = new AntPathMatcher();
                    for (String url : urls) {
                        if (antPathMatcher.match(url, request.getRequestURI())) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                //user可以访问的资源
                if("ROLE_USER".equals(authority.getAuthority())){
                    Set<String> urls = new HashSet();
                    urls.add("/test/**");
                    urls.add("/product/**");
                    AntPathMatcher antPathMatcher = new AntPathMatcher();
                    for (String url : urls) {
                        if (antPathMatcher.match(url, request.getRequestURI())) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
            }
            return hasPermission;
        } else {
            return false;
        }
    }
}
