package com.evatool.requirements.events;

// import com.evatool.global.event.RequirementCreated;

import com.evatool.global.event.RequirementCreated;
import com.evatool.requirements.controller.RequirementsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    Logger logger = LoggerFactory.getLogger(RequirementEventPublisher.class);



    public void publishRequirementEvent(RequirementCreated requirementCreated) {
        logger.info("Publishing custom event: " +  requirementCreated.getMessage());
        // RequirementCreated requirementCreated = new RequirementCreated(this, message);
        applicationEventPublisher.publishEvent(requirementCreated); //(requirementCreated);
    }

}
