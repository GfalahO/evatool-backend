package com.evatool.variants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VariantModule {
    public static void main(String[] args) {
        SpringApplication.run(VariantModule.class, args);
        System.out.println("Variant Module started successfully.");
    }
}
