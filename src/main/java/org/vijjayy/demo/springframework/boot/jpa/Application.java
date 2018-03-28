package org.vijjayy.demo.springframework.boot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vijjayy.demo.springframework.boot.jpa.repository.EmployeesRespository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = EmployeesRespository.class)
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class); 
	}
	
	public static void main(String... args) {
		
		SpringApplication.run(Application.class, args);//NOSONAR
	}
	
}