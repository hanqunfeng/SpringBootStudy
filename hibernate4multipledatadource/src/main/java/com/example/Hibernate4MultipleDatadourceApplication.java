package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Hibernate4MultipleDatadourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hibernate4MultipleDatadourceApplication.class, args);
	}
}
