package com.demo.mysqlconnect.service.impl;

import com.demo.mysqlconnect.mapper.StudentMapper;
import com.demo.mysqlconnect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getAllStudents() {
        return this.studentMapper.getAllStudents();
    }
}
