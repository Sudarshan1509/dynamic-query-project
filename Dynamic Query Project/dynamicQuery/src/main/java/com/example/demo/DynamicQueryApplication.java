package com.example.demo;

import com.example.demo.repository.QuerySimpleJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories(
		repositoryBaseClass = QuerySimpleJpaRepository.class
)
public class DynamicQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicQueryApplication.class, args);
	}

}
