package com.example.mongo.dao;


import com.example.mongo.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/10/24 18:03.
 */


public interface PersonRepository extends MongoRepository<Person, String> {

    //等于
    List<Person> findByName(String name);

    //And --- 等价于 SQL 中的 and 关键字；
    List<Person> findByNameAndAge(String name, Integer age);

    // Or --- 等价于 SQL 中的 or 关键字；
    List<Person> findByNameOrAge(String name, Integer age);

    //分页
    Page<Person> findByNameNot(String name, Pageable pageable);



    //mongo原生查询语句
    //等于
    @Query("{'age':?0}")
    List<Person> withQueryFindByAge(Integer age);


    //大于
    @Query("{'age': {'$gt' : ?0}}")
    List<Person> findByAgeGreaterThan(int age);

    //正则匹配name，age范围
    @Query("{ 'name':{'$regex':?0,'$options':'i'}, 'age': {'$gte':?1,'$lte':?2}}")
    public Page<Person> findByNameAndAgeRange(String name,int ageFrom,int ageTo,Pageable page);


    //正则匹配name，age范围，查询结果只封装name和age，当然默认ID是必须封装的
    @Query(value = "{ 'name':{'$regex':?0,'$options':'i'}, 'age': {'$gte':?1,'$lte':?2}}",fields = "{ 'name' : 1, 'age' : 1}")
    public Page<Person> findByNameAndAgeRangeShow(String name,int ageFrom,int ageTo,Pageable page);


}
