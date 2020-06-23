package com.demo.auth.service;

import com.demo.auth.bean.Role;

import java.util.List;

public interface UserRoleService {
    List<Role> findRolesByUserName(String userName);
}
