package com.example.service;

import com.example.mapper.PersonMapper;
import com.example.pojo.Person;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
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

    @Transactional(readOnly = true)
    public List<Person> getAllPersonList(){
        return personMapper.selectAll();
    }

    @Transactional(readOnly = true)
    public List<Person> getPagePersonList(Person person, RowBounds rowBounds){

        return personMapper.selectByRowBounds(person,rowBounds);

    }

    public List<Person> getPagePersonList(Person person){
        if (person.getPage() != null && person.getRows() != null) {
            PageHelper.startPage(person.getPage(), person.getRows(), "p_id");
        }
        return personMapper.selectAll();
    }


    public List<Person> queryListByParam(Person person){
        if (person.getPage() != null && person.getRows() != null) {
            PageHelper.startPage(person.getPage(), person.getRows(), "p_id");
        }
        return personMapper.queryListByParam(person);
    }

    public List<Person> queryListByParamSelective(Person person){
        if (person.getPage() != null && person.getRows() != null) {
            PageHelper.startPage(person.getPage(), person.getRows(), "p_id");
        }
        return personMapper.queryListByParamSelective(person);
    }
}
