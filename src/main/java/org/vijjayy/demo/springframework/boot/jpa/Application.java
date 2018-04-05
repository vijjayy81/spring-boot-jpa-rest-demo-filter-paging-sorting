package org.vijjayy.demo.springframework.boot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * 
 * Demo application for Spring boot with REST API, Swagger codegen, Spring Data JPA, Hibernate, with Filter, Paging and Sorting 
 * 
 * @author Vijjayy
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class); 
	}
	
	public static void main(String... args) {
		
		SpringApplication.run(Application.class, args);//NOSONAR
	}
	
}