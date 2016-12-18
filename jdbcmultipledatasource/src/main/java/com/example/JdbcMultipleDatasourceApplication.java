package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class JdbcMultipleDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcMultipleDatasourceApplication.class, args);
	}
}
