package com.assignment.PhoneBookApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.assignment.PhoneBookApp.repository")
@SpringBootApplication
@EnableCaching
public class PhoneBookApplication extends SpringBootServletInitializer  {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		  return application.sources(PhoneBookApplication.class);
		 }
	
	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
	}

}
