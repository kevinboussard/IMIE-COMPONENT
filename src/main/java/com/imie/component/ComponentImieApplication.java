/**
 * General Package
 */
package com.imie.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"entity","controller","form","repository","view","tool", "com"})
@EnableJpaRepositories(basePackages = "com")
@EntityScan(basePackages = {"com"})
public class ComponentImieApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ComponentImieApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ComponentImieApplication.class, args);
	}
}
