package com.demo.mybatis.service;

import com.demo.mybatis.bean.Student;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    Student queryStudentBySno(String sno);
}
