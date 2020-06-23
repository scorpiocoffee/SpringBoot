package com.demo.auth.service;

import com.demo.auth.bean.User;

public interface UserService {
    User findUserByUserName(String userName);
}
