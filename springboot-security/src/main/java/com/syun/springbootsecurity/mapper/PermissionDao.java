package com.syun.springbootsecurity.mapper;

import com.syun.springbootsecurity.domain.Permission;

import java.util.List;

/**
 * Created by yangyibo on 17/1/20.
 */
public interface PermissionDao {
    public List<Permission> findAll();

    public List<Permission> findByAdminUserId(int userId);
}
