package com.hostshw.demohosts.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hostshw.demohosts.rest", "com.hostshw.demohosts.model"})
@EnableJpaRepositories(basePackages = {"com.hostshw.demohosts.model"})
@EntityScan(basePackages = {"com.hostshw.demohosts.model"})
public class DemohostsApplication {
	
	@GetMapping("/message") //to ensure we deployed succefully to azure
	public String msg() {
		return "Deployed Succesfully";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemohostsApplication.class, args);
	}

}
