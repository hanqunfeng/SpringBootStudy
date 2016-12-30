package com.example.redis.domain;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/26 18:06.
 */

//必须实现序列化接口，另外需要提供一个空的构造函数，因为Jackson序列化对象时需要使用空构造函数
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Integer age;

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
