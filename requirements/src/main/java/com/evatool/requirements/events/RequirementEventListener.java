package com.evatool.requirements.events;


import com.evatool.global.events.RequirementCreatedEvent;
import com.evatool.requirements.controller.RequirementsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    Logger logger = LoggerFactory.getLogger(RequirementsController.class);

    @EventListener
    public void onRequirementCreatedListener(RequirementCreatedEvent event){
        logger.info("event: " + event.getClass() + " " + event.getSource());
    }

}
