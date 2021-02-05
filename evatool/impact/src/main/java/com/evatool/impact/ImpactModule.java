package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: [hbuhl] make all tests more entity agnostic (variable names)
// TODO: Swagger doc
// TODO: What about other documentation?
// TODO: API Specification
// TODO: Eventing Specification
// TODO: Use Rest level 3

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
        System.out.println("Impact Module started successfully.");
    }
}
