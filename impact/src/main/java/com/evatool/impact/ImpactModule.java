package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Features:
    // TODO Better error handling for possible rest calls
    // TODO Events
    // TODO More Impact URIs

// Tests:
    // TODO [tzaika] Implement Dimension Tests
    // TODO [tzaika] Implement Impact Tests
    // TODO Cover all scenarios with Tests
    // TODO use @BeforeEach and other best practice things in Tests
    // TODO Controller util classes
    // TODO Correct ErrorMessage return in RestController (Mock and integration test)
    // TODO Correct Json of Rest Controller [Rest Level 3] (Mock tests only)
    // TODO Event Tests

// TODO [tzaika] Test if real events are asynchronous

// TODO [tzaika & hbuhl] API Specification
// TODO [tzaika & hbuhl] Eventing Specification

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
