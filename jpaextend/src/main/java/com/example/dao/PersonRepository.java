package com.example.dao;

import com.example.BaseJpaRepository;
import com.example.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/21 16:48.
 */

public interface PersonRepository extends BaseJpaRepository<Person, Integer> {


    //等于
    List<Person> findByPName(String PName);

    //And --- 等价于 SQL 中的 and 关键字；
    List<Person> findByPNameAndPAge(String PName, Integer PAge);

    // Or --- 等价于 SQL 中的 or 关键字；
    List<Person> findByPNameOrPAge(String PName, Integer PAge);

    //Top --- 查询符合条件的前两条记录，等价与First关键字
    List<Person> findTop2ByPName(String PName);

    //2.以下方法基于@Query注解，方法名称可以随意，可用于查询和更新方法，更新方法要设置@Modifying注解
    //使用命名参数
    @Query("select p from Person p where p.pName = :name and p.pAge = :age")
    List<Person> withNameAndAgeQuery(@Param("name") String name, @Param("age") Integer age);

    //使用参数索引
    @Query("select p from Person p where p.pName = ?1 and p.pAge = ?2")
    List<Person> withNameAndAgeQuery2(String name, Integer age);


    //删除操作，使用hql，如果要使用sql，需要增加nativeQuery = true
    @Query(value = "delete from Person where pId=?1")
    @Modifying
    int deletePersonById(Integer id);

    //修改操作
    @Query(value = "update Person set pName=?1 where pId=?2 ")
    @Modifying
    int updatePersonName(String name, Integer id);

    //插入操作，使用sql操作
    @Query(value = "insert into person(p_name,p_age) value(?1,?2)",nativeQuery = true)
    @Modifying
    int insertPersonByParam(String name, Integer age);


    //3以下方法实现分页查询功能，只需要在方法中增加Pageable pageable参数即可，返回结果为Page集合
    Page<Person> findByPNameNot(String name, Pageable pageable);

    //使用命名参数
    @Query("select p from Person p where p.pName = :name ")
    Page<Person> withNameQueryPage(@Param("name") String name, Pageable pageable);
}
