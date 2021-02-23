package com.evatool.requirements.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    Logger logger = LoggerFactory.getLogger(RequirementEventPublisher.class);

    public void publishEvent(ApplicationEvent applicationEvent){
        logger.info("Publishing Event: "+ applicationEvent.getClass());
        applicationEventPublisher.publishEvent(applicationEvent);
    }

}
