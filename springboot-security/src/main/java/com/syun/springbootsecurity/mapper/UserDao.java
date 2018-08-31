package com.syun.springbootsecurity.mapper;

import com.syun.springbootsecurity.domain.SysUser;


public interface UserDao {
    public SysUser findByUserName(String username);
}
