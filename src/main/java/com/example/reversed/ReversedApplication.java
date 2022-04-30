package com.example.reversed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReversedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReversedApplication.class, args);
	}

}
