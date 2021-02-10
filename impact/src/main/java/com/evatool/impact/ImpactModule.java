package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Features:
// TODO [hbuhl & tzaika] Events

// Tests:
// TODO [tzaika] Implement Impact Tests
// TODO [hbuhl] ErrorMessage tests in RestController?
// TODO [hbuhl & tzaika] Event Tests

// General:
// TODO [tzaika] Test if real events are asynchronous
// TODO [tzaika] ImpactRest with Swagger

@SpringBootApplication
public class ImpactModule {
	public static void main(String[] args) {
		SpringApplication.run(ImpactModule.class, args);
	}
}
