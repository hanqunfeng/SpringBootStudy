package com.example.service;

import com.example.hibernate.CP_HibernateDAO;
import com.example.model.ds1.Person;
import com.example.model.ds2.Person2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/15 16:04.
 */

@Service
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class PersonService {

    @Resource(name = "hibernateDAO")
    private CP_HibernateDAO cp_hibernateDAO;

    @Resource(name = "hibernateDAO2")
    private CP_HibernateDAO cp_hibernateDAO2;


    public void savePserson(Person person, Person2 person2) {
        cp_hibernateDAO.save(person);
        cp_hibernateDAO2.save(person2);
    }

    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<Person> getAllPersonList() {

        List<Person> list = (List<Person>) cp_hibernateDAO.findAll(Person.class, new String[]{});
        return list;
    }

    @Transactional(transactionManager = "transactionManager", readOnly = true)
    public List<Person2> getAllPerson2List() {

        List<Person2> list = (List<Person2>) cp_hibernateDAO2.findAll(Person2.class, new String[]{});
        return list;
    }


}
