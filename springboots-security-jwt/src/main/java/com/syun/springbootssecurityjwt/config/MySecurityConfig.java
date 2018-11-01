package com.syun.springbootssecurityjwt.config;


import com.syun.springbootssecurityjwt.handler.*;
import com.syun.springbootssecurityjwt.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final AjaxLogoutSuccessHandler logoutSuccessHandler;

    private final AjaxAuthenticationEntryPoint authenticationEntryPoint;

    private final AjaxAccessDeniedHandler accessDeniedHandler;

    private final AjaxAuthenticationFailureHandler authenticationFailureHandler;

    private final AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    private final MyUserDetailsService myUserDetailsService;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    public MySecurityConfig(AjaxLogoutSuccessHandler logoutSuccessHandler, AjaxAuthenticationEntryPoint authenticationEntryPoint, AjaxAccessDeniedHandler accessDeniedHandler, AjaxAuthenticationFailureHandler authenticationFailureHandler, AjaxAuthenticationSuccessHandler authenticationSuccessHandler, MyUserDetailsService myUserDetailsService, JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig) {
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.smsCodeAuthenticationSecurityConfig = smsCodeAuthenticationSecurityConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //取消session
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest()
                //使用rbac 角色绑定资源的方式
                .access("@rbacauthorityservice.hasPermission(request,authentication)")
                //.authenticated()
                .and()
                //该url比较特殊,需要和login.html的form的action的的url一致
                .formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).permitAll()
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll()
                .and()
                .csrf().disable();
        http.apply(smsCodeAuthenticationSecurityConfig);
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(myUserDetailsService).tokenValiditySeconds(300);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //使用jwt的Authentication
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //使用数据库
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }



}
