package com.evatool.requirements.events;

// import com.evatool.global.event.RequirementCreated;

import com.evatool.global.event.RequirementCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// implements ApplicationListener<RequirementCreated>
@Component
public class RequirementEventListener {

    Logger logger = LoggerFactory.getLogger(RequirementEventListener.class);

     @EventListener
     public void onApplicationEvent(RequirementCreated requirementCreated) {
         logger.info("Received event: " + requirementCreated.getMessage() + "\n");
     }
}
