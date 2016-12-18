package com.example.dao;

import com.example.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/15 15:41.
 */
@Repository
public class PersonDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    public int savePerson(Person person){
        String sql = "INSERT INTO `springboot1`.`person` (`p_name`, `p_age`) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql,new Object[]{person.getName(),person.getAge()});
        return result;
    }

    public List<Person> getAllPersonList(){
        String sql = "select * from person s";
        List<Person> list = jdbcTemplate.query(sql,new PersonMapper());
        return list;
    }




    class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getLong("p_id"));
            person.setName(resultSet.getString("p_name"));
            person.setAge(resultSet.getInt("p_age"));
            return person;
        }
    }



}
