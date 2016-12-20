package com.example.service;

import com.example.mapper.ds1.PersonMapper;
import com.example.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/19 15:42.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private com.example.mapper.ds2.PersonMapper personMapper2;

    public int insert(Person person){
        int ds1_result = personMapper.insert(person);
        int ds2_result = personMapper2.insert(person);
        return ds1_result + ds2_result;
    }

    @Transactional(readOnly = true)
    public Person selectByPrimaryKey(Integer pId){
        return personMapper.selectByPrimaryKey(pId);
    }
}
