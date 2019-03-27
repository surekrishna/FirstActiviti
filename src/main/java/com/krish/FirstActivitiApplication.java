package com.krish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.krish.activiti.service.HungerService;

@SpringBootApplication
public class FirstActivitiApplication {

	@Autowired
	private HungerService hungerService;
	
	public static void main(String[] args) {
		SpringApplication.run(FirstActivitiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init() {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				hungerService.createPersons();
				
			}
		};
	}

}
