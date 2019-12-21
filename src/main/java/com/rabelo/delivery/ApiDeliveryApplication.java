package com.rabelo.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rabelo.delivery.domain.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ApiDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeliveryApplication.class, args);
	}

}
