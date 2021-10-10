package com.bithumb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InterestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterestApplication.class, args);
	}

}
