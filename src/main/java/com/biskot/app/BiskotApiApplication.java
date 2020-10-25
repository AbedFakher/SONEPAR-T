package com.biskot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.biskot")
@EnableSwagger2
public class BiskotApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiskotApiApplication.class, args);
	}

}
