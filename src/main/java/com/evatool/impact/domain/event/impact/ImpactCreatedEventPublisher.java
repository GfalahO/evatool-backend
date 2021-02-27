package com.evatool.impact.domain.event.impact;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.impact.domain.event.json.mapper.ImpactJsonMapper;
import com.evatool.impact.domain.entity.Impact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ImpactCreatedEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ImpactCreatedEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public ImpactCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onImpactCreated(final Impact impact) {
        logger.info("Preparing to publish event");
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactCreatedEvent = new ImpactCreatedEvent(this, impactJson.toString());
        applicationEventPublisher.publishEvent(impactCreatedEvent);
        logger.info("Event published");
    }
}
