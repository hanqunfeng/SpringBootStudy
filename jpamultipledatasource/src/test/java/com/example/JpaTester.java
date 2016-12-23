package com.example;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/23 15:05.
 */


public class JpaTester extends JpaMultipleDatasourceApplicationTests {

    @Resource(name = "personRepository")
    private com.example.dao.ds1.PersonRepository personRepository;

    @Resource(name = "personRepository2")
    private com.example.dao.ds2.PersonRepository personRepository2;



    @Test
    public void saveTwoRepository(){

        com.example.model.ds1.Person person = new com.example.model.ds1.Person();
        person.setpName("demo");
        person.setpAge(10);
        personRepository.saveAndFlush(person);

        com.example.model.ds2.Person person2 = new com.example.model.ds2.Person();
        person2.setpName("demo");
        person2.setpAge(10);
        personRepository2.saveAndFlush(person2);

        System.out.println(person);
        System.out.println(person2);
    }
}
