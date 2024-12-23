package com.example.book_tracker_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTrackerServiceApplication.class, args);
	}

}
