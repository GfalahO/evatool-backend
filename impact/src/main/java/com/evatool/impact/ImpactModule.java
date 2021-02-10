package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Features:
// TODO Better error handling for possible rest calls
// TODO Events

// Tests:
// TODO [hbuhl] Copy Stakeholder Tests to Dimension Tests and remove unnecessary Stakeholder Tests (API spec)
// TODO [tzaika] Implement Impact Tests

// TODO DimensionRestControllerTests: use @BeforeEach in nested classes to save lines
// TODO Correct ErrorMessage return in RestController (Mock and integration test)

// TODO Controller util classes
// TODO JsonMapper Tests
// TODO Event Tests

// General:
// TODO [hbuhl] Split GitHub Action into unit and integration tests?
// TODO [hbuhl] use real database instead of in-memory?

// TODO [tzaika] Test if real events are asynchronous

// TODO [tzaika] ImpactDTO in wiki
// TODO [tzaika] Rename *Mapper to *DtoMapper

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
