package com.example;

import com.example.model.Person;
import com.example.service.PersonService;
import org.junit.Assert;
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
public class JdbcMultipleDatasourceApplicationTests {

	@Autowired
	private PersonService personService;

	@Test
	public void savePerson(){
		Person person = new Person();
		person.setName("王五");
		person.setAge(18);
		int result = personService.savePserson(person);
		Assert.assertEquals(2,result);
	}

	@Test
	public void getAllPersonList(){
		List<Person> list = personService.getAllPersonList();
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

}
