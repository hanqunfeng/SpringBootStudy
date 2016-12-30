package com.example;

import com.example.mongo.dao.PersonRepository;
import com.example.mongo.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Iterator;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/26 17:43.
 */


public class MongoTester extends NosqlMongoRedisApplicationTests {

    @Autowired
    PersonRepository personRepository;



    @Test
    public void repositoryFindbyNameTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(personRepository.findByName("张三"));
        System.out.println("repositoryFindbyNameTest content == " + content);


    }

    @Test
    public void q1Test() throws Exception {
        String uri = "/q1?name=张三";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("q1Test content == " + content);
    }

    @Test
    public void saveTest() throws Exception {
        String uri = "/save?name=张三&age=10";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("saveTest content == " + content);
    }

    @Test
    public void q2Test() throws Exception {
        String uri = "/q2?age=10";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("saveTest content == " + content);
    }


    @Test
    public void findByAgeGreaterThan(){
        List<Person> list =  personRepository.findByAgeGreaterThan(8);
        for(Person person : list){
            System.out.println(person);
        }
    }

    @Test
    public void findByNameNot(){
        String name = "哈哈";
        //排序
        Sort sort = new Sort(Sort.Direction.DESC, "pId");
        //查询第一页，按一页三行分页
        Pageable pageable = new PageRequest(0, 3, sort);

        Page<Person> pages = personRepository.findByNameNot(name,pageable);
        System.out.println("pages.getTotalElements()" + pages.getTotalElements());
        System.out.println("pages.getTotalPages()" + pages.getTotalPages());
        Iterator<Person> it=pages.iterator();
        while(it.hasNext()){
            System.out.println("value:"+((Person)it.next()));
        }
    }

    @Test
    public void findByNameAndAgeRange(){
        String name = "张";
        int ageFrom = 5;
        int ageTo = 11;
        //排序
        Sort sort = new Sort(Sort.Direction.DESC, "pId");
        //查询第一页，按一页三行分页
        Pageable pageable = new PageRequest(0, 3, sort);
        Page<Person> pages = personRepository.findByNameAndAgeRange(name,ageFrom,ageTo,pageable);
        System.out.println("pages.getTotalElements()" + pages.getTotalElements());
        System.out.println("pages.getTotalPages()" + pages.getTotalPages());
        Iterator<Person> it=pages.iterator();
        while(it.hasNext()){
            System.out.println("value:"+((Person)it.next()));
        }
    }

    @Test
    public void findByNameAndAgeRangeShow(){
        String name = "张";
        int ageFrom = 5;
        int ageTo = 11;
        //排序
        Sort sort = new Sort(Sort.Direction.DESC, "pId");
        //查询第一页，按一页三行分页
        Pageable pageable = new PageRequest(0, 3, sort);
        Page<Person> pages = personRepository.findByNameAndAgeRangeShow(name,ageFrom,ageTo,pageable);
        System.out.println("pages.getTotalElements()" + pages.getTotalElements());
        System.out.println("pages.getTotalPages()" + pages.getTotalPages());
        Iterator<Person> it=pages.iterator();
        while(it.hasNext()){
            System.out.println("value:"+((Person)it.next()));
        }
    }
}
