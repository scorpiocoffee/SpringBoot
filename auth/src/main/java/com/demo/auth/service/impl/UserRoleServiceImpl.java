package com.demo.auth.service.impl;

import com.demo.auth.bean.Role;
import com.demo.auth.mapper.UserRoleMapper;
import com.demo.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findRolesByUserName(String userName) {
        return userRoleMapper.findRolesByUserName(userName);
    }
}
