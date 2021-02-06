package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO [hbuhl] determine what needs to be tested where.
// TODO [hbuhl] make all tests more entity agnostic (variable names)
// TODO [hbuhl] Better testing data setup.
// TODO [hbuhl] Use real database for tests (toggle between in-memory and real db)
// TODO [hbuhl] Complete more elaborate testing setup in this class and copy it to other classes + test all cases!
// TODO [hbuhl] Use Rest level 3 in all Stakeholder Rest APIs (what links are necessary?)

// TODO Swagger doc
// TODO What about other documentation?
// TODO API Specification
// TODO Eventing Specification

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
