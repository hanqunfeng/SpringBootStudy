package com.example.mapper;

import com.example.pojo.Person;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/20 17:11.
 */


public class PersonSqlProvider {

    public String selectSelective(Person record){
        BEGIN();

        SELECT("p_id, p_name, p_age");
        FROM("person");
        WHERE("1=1");

        if(record.getStartAge()!=null){
            AND();
            WHERE("p_age >= #{startAge}");
        }

        if(record.getEndAge()!=null){
            AND();
            WHERE("p_age <= #{endAge}");
        }


        return SQL();
    }

    
}
