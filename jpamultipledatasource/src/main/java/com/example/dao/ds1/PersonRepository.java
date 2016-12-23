package com.example.dao.ds1;


import com.example.model.ds1.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/21 16:48.
 */

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Integer> {

    //1.以下方法基于属性名称和查询关键字，所以方法名称必须遵循命名规则，并且参数类型要与实体的参数类型一致。
    // 只用于查询方法，以下给出常用的示例

    //等于
    List<Person> findByPName(String PName);

    //And --- 等价于 SQL 中的 and 关键字；  
    List<Person> findByPNameAndPAge(String PName, Integer PAge);

    // Or --- 等价于 SQL 中的 or 关键字；  
    List<Person> findByPNameOrPAge(String PName, Integer PAge);

    //Between --- 等价于 SQL 中的 between 关键字；  
    List<Person> findByPAgeBetween(Integer min, Integer max);

    //LessThan --- 等价于 SQL 中的 "<"；  日期类型也可以使用Before关键字
    List<Person> findByPAgeLessThan(Integer max);

    //LessThanEqual --- 等价于 SQL 中的 "<="；
    List<Person> findByPAgeLessThanEqual(Integer max);

    //GreaterThan --- 等价于 SQL 中的">"；日期类型也可以使用After关键字
    List<Person> findByPAgeGreaterThan(Integer min);

    //GreaterThanEqual --- 等价于 SQL 中的">="；
    List<Person> findByPAgeGreaterThanEqual(Integer min);

    //IsNull --- 等价于 SQL 中的 "is null"；
    List<Person> findByPNameIsNull();

    //IsNotNull --- 等价于 SQL 中的 "is not null"；
    List<Person> findByPNameIsNotNull();

    //NotNull --- 与 IsNotNull 等价；  
    List<Person> findByPNameNotNull();

    //Like --- 等价于 SQL 中的 "like";
    List<Person> findByPNameLike(String PName);

    //NotLike --- 等价于 SQL 中的 "not like"；
    List<Person> findByPNameNotLike(String PName);

    //OrderBy --- 等价于 SQL 中的 "order by"；
    List<Person> findByPNameNotNullOrderByPAgeAsc();

    //Not --- 等价于 SQL 中的 "！ ="；
    List<Person> findByPNameNot(String PName);

    //In --- 等价于 SQL 中的 "in";
    List<Person> findByPNameIn(String PName);

    //NotIn --- 等价于 SQL 中的 "not in";
    List<Person> findByPNameNotIn(String PName);


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
    @Query(value = "insert into person(p_name,p_age) value(?1,?2)", nativeQuery = true)
    @Modifying
    int insertPersonByParam(String name, Integer age);


    //3以下方法实现分页查询功能，只需要在方法中增加Pageable pageable参数即可，返回结果为Page集合
    Page<Person> findByPNameNot(String name, Pageable pageable);

    //使用命名参数
    @Query("select p from Person p where p.pName = :name ")
    Page<Person> withNameQueryPage(@Param("name") String name, Pageable pageable);
}
