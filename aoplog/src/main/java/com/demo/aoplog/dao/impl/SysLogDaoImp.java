package com.demo.aoplog.dao.impl;

import com.demo.aoplog.domain.SysLog;
import com.demo.aoplog.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SysLogDaoImp implements SysLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveSysLog(SysLog sysLog) {
        StringBuilder sql = new StringBuilder("insert into sys_log ");
        sql.append("(username, operation, time, method, params, ip, create_time) ");
        sql.append("values(:username, :operation, :time, :method, :params, :ip, :createTime)");

        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(sysLog));
    }
}
