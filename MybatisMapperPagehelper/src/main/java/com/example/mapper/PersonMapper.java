package com.example.mapper;

import com.example.MyMapper;
import com.example.pojo.Person;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PersonMapper extends MyMapper<Person> {

    @Select({
            "select",
            "p_id, p_name, p_age",
            "from person",
            "where  p_age between #{startAge} and #{endAge}"
    })
    @Results({
            @Result(column="p_id", property="pId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="p_name", property="pName", jdbcType=JdbcType.VARCHAR),
            @Result(column="p_age", property="pAge", jdbcType=JdbcType.INTEGER)
    })
    List<Person> queryListByParam(Person person);

    @SelectProvider(type = PersonSqlProvider.class,method = "selectSelective")
    @Results({
            @Result(column="p_id", property="pId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="p_name", property="pName", jdbcType=JdbcType.VARCHAR),
            @Result(column="p_age", property="pAge", jdbcType=JdbcType.INTEGER)
    })
    List<Person> queryListByParamSelective(Person person);



}