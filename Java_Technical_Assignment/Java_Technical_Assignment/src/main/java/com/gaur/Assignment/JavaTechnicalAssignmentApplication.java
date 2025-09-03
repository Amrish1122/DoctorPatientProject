package com.gaur.Assignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Doctor Patient Rest API",
		version = "1.0.0",
		description = "This is Rest API with rest endpoints for doctors and patients"
))
public class JavaTechnicalAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalAssignmentApplication.class, args);
	}

}
