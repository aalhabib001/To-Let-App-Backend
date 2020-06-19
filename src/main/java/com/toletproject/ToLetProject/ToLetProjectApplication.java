package com.toletproject.ToLetProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ToLetProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToLetProjectApplication.class, args);
	}

}
