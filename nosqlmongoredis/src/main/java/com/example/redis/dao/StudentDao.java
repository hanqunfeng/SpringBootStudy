package com.example.redis.dao;

import com.example.redis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/26 18:11.
 */

@Repository
public class StudentDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valueOperationsStr;

    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valueOperations;

    public void setString(String key,String value){
        valueOperationsStr.set(key,value);
    }

    public String getString(String key){
        return valueOperationsStr.get(key);
    }

    public void saveStudent(Student student){
        valueOperations.set(student.getId(),student);
    }

    public Student getStudent(String id){
        return (Student)valueOperations.get(id);
    }


}
