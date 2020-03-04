package com.example.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.vehicle"})
public class VehicleApplication {

	//The vehicle application run starts from here
	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
	}

}
