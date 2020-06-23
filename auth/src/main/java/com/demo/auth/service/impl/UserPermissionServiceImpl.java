package com.demo.auth.service.impl;

import com.demo.auth.bean.Permission;
import com.demo.auth.mapper.UserPermissionMapper;
import com.demo.auth.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<Permission> findPermissionsByUserName(String userName) {
        return this.userPermissionMapper.findPermissionsByUserName(userName);
    }
}
