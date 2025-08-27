package com.collabify.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@RestController
public class CollabifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollabifyApplication.class, args);
	}
	@GetMapping("/")
	public String index() {
		return String.format("Welcome to Collabify Spring!");
	}
	@GetMapping("/hello")
	public String hello(@RequestParam (value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s, Welcome to Collabify Spring API!", name);
	}
	

}
