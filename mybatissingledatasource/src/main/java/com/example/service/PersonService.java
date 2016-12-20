package com.example.service;

import com.example.mapper.PersonMapper;
import com.example.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/19 15:42.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class PersonService {
    @Autowired
    private PersonMapper personMapper;

    public int insert(Person person){
        return personMapper.insert(person);
    }

    @Transactional(readOnly = true)
    public Person selectByPrimaryKey(Integer pId){
        return personMapper.selectByPrimaryKey(pId);
    }


}
