package com.zinkworks.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.zinkworks.atm.db")
@EntityScan("com.zinkworks.atm.db.*")
@ComponentScan("com.zinkworks.atm.*")
@SpringBootApplication
public class AtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

}
