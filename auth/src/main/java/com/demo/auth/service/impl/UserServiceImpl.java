package com.demo.auth.service.impl;

import com.demo.auth.bean.User;
import com.demo.auth.mapper.UserMapper;
import com.demo.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }
}
