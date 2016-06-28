package com.imie.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"entity","controller","repository","com"})
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = {"com"})
public class ComponentImieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentImieApplication.class, args);
	}
}
