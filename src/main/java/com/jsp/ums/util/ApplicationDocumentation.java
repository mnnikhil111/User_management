package com.jsp.ums.util;

import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {

	Contact contact() {
		return new Contact().email("mnnikhil92@gmail.com")
		.url("xyz.abc.in")
		.email("abc");
		
	}
	
	Info info() {
		return new Info().title("User Management System API")
				.version("1.0v")
				.description("User Management system is a Restful API built using"+" spring Boot and MySQL database")
				.contact(contact());
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
}
