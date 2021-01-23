package com.fae.evatool.impact;

import com.fae.evatool.impact.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImpactModule {

    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
        System.out.println("Impact Module started successfully.");

        // Manual Tests.
        ImpactDimension safetyDimension = new ImpactDimension("Safety", "...");
        System.out.println(safetyDimension.toString());

        Impact goal1 = new Impact(1, "...");
        System.out.println(goal1.toString());

        Project project = new Project();
        System.out.println(project.toString());

        Requirement requirement = new Requirement();
        System.out.println(requirement.toString());

        Scenario scenario = new Scenario();
        System.out.println(scenario.toString());

        Stakeholder stakeholder = new Stakeholder();
        System.out.println(stakeholder.toString());

    }
}
