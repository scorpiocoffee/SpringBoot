package com.demo.auth.mapper;

import com.demo.auth.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoleMapper {
    @Select("select r.name, r.memo from t_role r " +
            "left join t_user_role ur on(r.id=ur.rid)" +
            "left join t_user u on(u.id=ur.user_id)" +
            "where u.username=#{userName}")
    @Results(id = "user_role", value = {
        @Result(property = "userName", column = "name", javaType = String.class),
        @Result(property = "memo", column = "memo", javaType = String.class)
    })
    List<Role> findRolesByUserName(String userName);
}
