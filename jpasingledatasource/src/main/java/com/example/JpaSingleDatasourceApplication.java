package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class JpaSingleDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSingleDatasourceApplication.class, args);
	}
}
