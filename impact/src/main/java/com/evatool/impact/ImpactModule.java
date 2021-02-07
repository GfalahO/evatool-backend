package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO Move addLinks to static class (for better separation and tests)

// TODO Implement error handling for most cases (return helpful errors and links to help the frontend developers)
// TODO Tests for Rest level 3 links (Uri classes and RestSetting)
// TODO More tests for Rest Controller - both ServiceMock and Integration test (validate error message, Json and Rest Level 3 Links)

// TODO [tzaika] Implement Dimension Tests
// TODO [tzaika] Implement Impact Tests
// TODO [tzaika] Test if real events are asynchronous

// TODO [tzaika & hbuhl] API Specification
// TODO [tzaika & hbuhl] Eventing Specification

// TODO Implement events and tests

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
