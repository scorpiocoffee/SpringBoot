package com.demo.mysqlconnect.controller;

import com.demo.mysqlconnect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("querystudents")
    public List<Map<String, Object>> queryStudents() {
        return this.studentService.getAllStudents();
    }
}
