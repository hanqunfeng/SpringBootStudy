package com.example.redis.controller;

import com.example.redis.dao.StudentDao;
import com.example.redis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/26 18:22.
 */

@RestController
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/saveStudent")
    public Student saveStudent(String id,String name,Integer age){
        Student student = new Student(id,name,age);
        studentDao.saveStudent(student);
        studentDao.setString(student.getId(),student.getName());
        return student;
    }

    @RequestMapping("/getStudent")
    public Student getStudent(String id){
        return studentDao.getStudent(id);
    }

    @RequestMapping("/getStr")
    public String getStr(String id){
        return studentDao.getString(id);
    }

}
