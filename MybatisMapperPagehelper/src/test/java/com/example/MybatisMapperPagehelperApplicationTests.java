package com.example;

import com.example.pojo.Person;
import com.example.service.PersonService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MybatisMapperPagehelperApplicationTests {


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

	@Test
	public void getAllPersonList(){
		List<Person> list = personService.getAllPersonList();
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void getPagePersonList(){
		Person person = new Person();
		person.setpName("王五");

		List<Person> list = personService.getPagePersonList(person,new RowBounds(2,3));
		System.out.println(list.size());
		for(Person p : list){
			System.out.println(p);
		}
	}

	@Test
	public void getPagePersonList2(){
		Person person = new Person();

		person.setPage(2);
		person.setRows(2);

		List<Person> list = personService.getPagePersonList(person);
		System.out.println(list.size());
		for(Person p : list){
			System.out.println(p);
		}
	}

	@Test
	public void queryListByParam(){
		Person person = new Person();

		person.setPage(2);
		person.setRows(2);

		person.setStartAge(15);
		person.setEndAge(22);

		List<Person> list = personService.queryListByParam(person);
		System.out.println(list.size());
		for(Person p : list){
			System.out.println(p);
		}
	}


	@Test
	public void queryListByParamSelective(){
		Person person = new Person();

		person.setPage(1);
		person.setRows(2);

		person.setStartAge(15);
		//person.setEndAge(22);

		List<Person> list = personService.queryListByParamSelective(person);
		System.out.println(list.size());
		for(Person p : list){
			System.out.println(p);
		}
	}

}
