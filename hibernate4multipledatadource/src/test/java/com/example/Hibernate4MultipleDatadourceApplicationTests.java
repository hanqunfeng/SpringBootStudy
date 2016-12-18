package com.example;

import com.example.model.ds1.Person;
import com.example.model.ds2.Person2;
import com.example.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
public class Hibernate4MultipleDatadourceApplicationTests {

	@Autowired
	private PersonService personService;

	@Test
	public void savePerson(){
		Person person = new Person();
		person.setName("王五");
		person.setAge(18);

		Person2 person2 = new Person2();
		person2.setName("赵六");
		person2.setAge(20);
		personService.savePserson(person,person2);

	}

	@Test
	public void getAllPersonList(){
		List<Person> list = personService.getAllPersonList();
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void getAllPerson2List(){
		List<Person2> list = personService.getAllPerson2List();
		System.out.println(list.size());
		for(Person2 person : list){
			System.out.println(person);
		}
	}

}
