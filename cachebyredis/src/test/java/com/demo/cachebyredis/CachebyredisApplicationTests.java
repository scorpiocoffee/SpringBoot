package com.demo.cachebyredis;

import com.demo.cachebyredis.bean.Student;
import com.demo.cachebyredis.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CachebyredisApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void test() throws Exception {
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("Student No.: " + student1.getSno());
        System.out.println("Student Name: " + student1.getName());

        student1.setName("Joy");
        this.studentService.update(student1);

        Student student2 = this.studentService.queryStudentBySno("002");
        System.out.println("Student No.: " + student2.getSno());
        System.out.println("Student Name: " + student2.getName());
    }
}
