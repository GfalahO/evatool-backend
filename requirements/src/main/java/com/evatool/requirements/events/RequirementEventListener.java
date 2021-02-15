package com.evatool.requirements.events;

import com.evatool.global.events.RequirementCreatedEvent;
import com.evatool.requirements.controller.RequirementsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    Logger logger = LoggerFactory.getLogger(RequirementEventListener.class);

    @EventListener
    public void handleSuccessful( RequirementCreatedEvent event) {
        logger.info("Event Message: " +event.getSource());

    }

}
