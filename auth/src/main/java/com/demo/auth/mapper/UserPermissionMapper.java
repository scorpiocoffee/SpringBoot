package com.demo.auth.mapper;

import com.demo.auth.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserPermissionMapper {
    @Select("select p.name, p.url, p.description from t_role r" +
            "left join t_user_role ur on(r.id=ur.rid)" +
            "left join t_user u on(u.id=ur.user_id)" +
            "left join t_role_permission rp on(rp.rid=r.id)" +
            "left join t_permission p on(p.id=rp.pid)" +
            "where u.username=#{userName}")
    @Results(id = "permission_user", value = {
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "url", column = "url", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class)
    })
    List<Permission> findPermissionsByUserName(String userName);
}
