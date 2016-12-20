package com.example;

import com.example.pojo.Person;
import com.example.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MybatisMultipleDatasourceApplicationTests {
	@Autowired
	PersonService personService;

	@Test
	public void selectByPrimaryKey(){
		Person person = personService.selectByPrimaryKey(1);
		System.out.println(person);
	}



	@Test
	public void insert(){
		Person person = new Person();
		person.setpName("王五");
		person.setpAge(18);
		System.out.println(personService.insert(person));

	}

}
