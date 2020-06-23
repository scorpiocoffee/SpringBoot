package com.demo.auth.mapper;

import com.demo.auth.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from t_user where username=#{userName}")
    @Results(id = "user", value = {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "userName", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "createdTime", column = "created_time", javaType = Date.class),
            @Result(property = "status", column = "status", javaType = String.class),
    })
    User findUserByUserName(String userName);
}
