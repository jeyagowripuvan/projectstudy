package com.casestudy.authmicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthmicroserviceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthmicroserviceApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Started auth microservice");
		SpringApplication.run(AuthmicroserviceApplication.class, args);
	}

}
