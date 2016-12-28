package com.example;

import com.example.dao.PersonRepository;
import com.example.model.Person;
import com.example.utils.SimpleSpecificationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaExtendApplicationTests {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void findAll() {
		List<Person> list = personRepository.findAll();
		for(Person person:list){
			System.out.println(person);
		}
	}

	@Test
	public void savePerson() {
		Person person = new Person();
		person.setpName("demo");
		person.setpAge(10);
		person = personRepository.saveAndFlush(person);
		System.out.println(person);
	}

	@Test
	public void findByAuto() {
		Person person = new Person();
		person.setpName("王五");
		person.setpAge(18);
		Sort sort = new Sort(Sort.Direction.DESC, "pId");
		//查询第一页，按一页三行分页
		Pageable pageable = new PageRequest(0, 3, sort);
		Page<Person> list = personRepository.findByAuto(person,pageable);
		for(Person p:list){
			System.out.println(p);
		}
	}

	@Test
	public void findByPName() {
		String name = "王五";
		List<Person> list = personRepository.findByPName(name);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void findByPNameAndPAge() {
		String name = "王五";
		int age = 18;
		List<Person> list = personRepository.findByPNameAndPAge(name,age);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void findByPNameOrPAge() {
		String name = "王五";
		int age = 25;
		List<Person> list = personRepository.findByPNameOrPAge(name,age);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void findTop2ByPName() {
		String name = "王五";
		List<Person> list = personRepository.findTop2ByPName(name);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void withNameAndAgeQuery() {
		String name = "王五";
		int age = 18;
		List<Person> list = personRepository.withNameAndAgeQuery(name,age);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}

	@Test
	public void withNameAndAgeQuery2() {
		String name = "王五";
		int age = 18;
		List<Person> list = personRepository.withNameAndAgeQuery2(name,age);
		System.out.println(list.size());
		for(Person person : list){
			System.out.println(person);
		}
	}


	@Test
	public void deletePersonById(){
		int id = 1;
		int result = personRepository.deletePersonById(id);
		System.out.println("result = " + result);
	}

	@Test
	public void updatePersonName(){
		int id = 1;
		String name = "哈哈";
		int result = personRepository.updatePersonName(name,id);
		System.out.println("result = " + result);
	}

	@Test
	public void insertPersonByParam(){
		int age = 10;
		String name = "哈哈";
		int result = personRepository.insertPersonByParam(name,age);
		System.out.println("result = " + result);
	}

	@Test
	public void findByPNameNot(){
		String name = "哈哈";
		//排序
		Sort sort = new Sort(Sort.Direction.DESC, "pId");
		//查询第一页，按一页三行分页
		Pageable pageable = new PageRequest(0, 3, sort);

		Page<Person> pages = personRepository.findByPNameNot(name,pageable);
		System.out.println("pages.getTotalElements()" + pages.getTotalElements());
		System.out.println("pages.getTotalPages()" + pages.getTotalPages());
		Iterator<Person> it=pages.iterator();
		while(it.hasNext()){
			System.out.println("value:"+((Person)it.next()));
		}
	}

	@Test
	public void withNameQueryPage(){
		String name = "王五";
		//排序
		Sort sort = new Sort(Sort.Direction.DESC, "pId");
		//查询第二页，按一页三行分页
		Pageable pageable = new PageRequest(1, 3, sort);

		Page<Person> pages = personRepository.withNameQueryPage(name,pageable);
		System.out.println("pages.getTotalElements()" + pages.getTotalElements());
		System.out.println("pages.getTotalPages()" + pages.getTotalPages());
		Iterator<Person> it=pages.iterator();
		while(it.hasNext()){
			System.out.println("value:"+((Person)it.next()));
		}
	}

	@Test
	public void getListBySql(){
		String sql = "select p_name , count(*) from person group by p_name";

		List<Object[]> list = personRepository.listBySQL(sql);
		for(Object[] object:list){
			System.out.println(object[0] + "##" +object[1]);
		}
	}

	@Test
	public void getListByHql(){
		String hql = "select pName , count(*) from Person group by pName";

		List<Object[]> list = personRepository.listByHQL(hql);
		for(Object[] object:list){
			System.out.println(object[0] + "##" +object[1]);
		}
	}

	@Test
	public void FindByDIY(){
		/**
		 * 这里的查询表示age大于20或者name中包含『五』
		 * 现在我们发现在SimpleSpecificationBuilder的add或者addOr方法中返回this的好处了
		 */
		List<Person> list = personRepository.findAll(
				new SimpleSpecificationBuilder("pAge",">",20)
						.addOr("pName",":","五")
						.generateSpecification());

		for(Person person : list){
			System.out.println(person);
		}
	}

}
