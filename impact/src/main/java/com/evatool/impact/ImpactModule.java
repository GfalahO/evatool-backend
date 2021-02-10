package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Features:
// TODO Events

// Tests:
// TODO [tzaika] Implement Impact Tests
// TODO Event Tests

// General:
// TODO [hbuhl] Split GitHub Action into unit and integration tests?
// TODO [hbuhl] use real database instead of in-memory?
// TODO [tzaika] Test if real events are asynchronous
// TODO [tzaika] ImpactRest with Swagger

@SpringBootApplication
public class ImpactModule {
	public static void main(String[] args) {
		SpringApplication.run(ImpactModule.class, args);
	}
}
