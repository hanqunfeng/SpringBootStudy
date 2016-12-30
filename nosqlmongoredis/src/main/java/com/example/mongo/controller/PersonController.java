package com.example.mongo.controller;


import com.example.mongo.dao.PersonRepository;
import com.example.mongo.domain.Location;
import com.example.mongo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;



/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/10/24 18:06.
 */

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, Integer age) {
        Person person = new Person(name, age);
        Collection<Location> locations = new LinkedHashSet<Location>();

        locations.add(new Location("上海", "2009"));
        locations.add(new Location("北京", "2010"));
        locations.add(new Location("南京", "2011"));

        person.setLocations(locations);

        return personRepository.save(person);
    }

    @RequestMapping("/q1")
    public List<Person> q1(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }
}
