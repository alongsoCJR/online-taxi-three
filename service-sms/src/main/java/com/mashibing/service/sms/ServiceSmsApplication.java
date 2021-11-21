package com.mashibing.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSmsApplication.class, args);
	}

	@GetMapping
	public String getApplicationName() {
		return "service-sms";
	}
}
