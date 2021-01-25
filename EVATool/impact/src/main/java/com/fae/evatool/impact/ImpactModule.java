package com.fae.evatool.impact;

import com.fae.evatool.impact.persistence.entity.Dimension;
import com.fae.evatool.impact.persistence.entity.Impact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
        System.out.println("Impact Module started successfully.");
    }
}
