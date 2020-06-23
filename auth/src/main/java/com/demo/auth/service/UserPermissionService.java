package com.demo.auth.service;

import com.demo.auth.bean.Permission;

import java.util.List;

public interface UserPermissionService {
    List<Permission> findPermissionsByUserName(String userName);
}
