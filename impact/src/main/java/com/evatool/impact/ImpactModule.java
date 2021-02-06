package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO [hbuhl] Better testing data setup.

// TODO [hbuhl] make code (business + tests) more entity agnostic (variable names)
// TODO [hbuhl] determine what needs to be tested where.
// TODO [hbuhl] Complete more elaborate testing setup in this class and copy it to other classes + test all cases!

// TODO [hbuhl] Use real database for tests (toggle between in-memory and real db). Overkill? Not necessary?

// TODO [tzaika] Test if real events are asynchronous

// TODO [tzaika & hbuhl] Swagger doc
// TODO [tzaika & hbuhl] What about other documentation?
// TODO [tzaika & hbuhl] API Specification
// TODO [tzaika & hbuhl] Eventing Specification

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
