package com.example.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.transfer")
public class InternalTransferSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternalTransferSystemApplication.class, args);
	}

}
