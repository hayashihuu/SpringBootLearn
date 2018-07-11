package com.syun.springboottest12.mapper;

import com.syun.springboottest12.dto.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryForList();

//    单表查询
    List<User> queryUserRole(Integer id);
}