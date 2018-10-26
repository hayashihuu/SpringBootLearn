package com.syun.springbootssecurityjwt.bean;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @program: springboots-security-jwt
 * @author: syun
 * @create: 2018-10-26 15:51
 */
@Data
public class User {

    private String username;

    private String password;

    private List<String> roles;



}
