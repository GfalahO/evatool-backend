package com.evatool.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalysisModule {

    public static void main(String[] args) {
        SpringApplication.run(AnalysisModule.class, args);
        System.out.println("Analysis Module startet");
        System.out.println("Test Push");

    }
}
