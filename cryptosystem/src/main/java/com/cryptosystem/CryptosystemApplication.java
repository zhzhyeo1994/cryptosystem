package com.cryptosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptosystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptosystemApplication.class, args);
	}

}
