package com.evatool;

import com.evatool.analysis.event.StakeholderCreatedEventPublisher;
import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvaToolApp {

    public static void main(String[] args) {
        SpringApplication.run(EvaToolApp.class, args);
        // var dummy = new ImpactDto();
        // dummy.setId("TestId");
        // dummy.setDescription("TestDescription");
        // System.out.println(dummy.toString());

    }
}
