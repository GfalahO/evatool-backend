package com.evatool.requirements.events;

// import com.evatool.global.event.RequirementCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RequirementEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishRequirementEvent(final String message) {
        System.out.println("Publishing custom event: " + message);
        // RequirementCreated requirementCreated = new RequirementCreated(this, message);
        applicationEventPublisher.publishEvent(null); //(requirementCreated);
    }

}
