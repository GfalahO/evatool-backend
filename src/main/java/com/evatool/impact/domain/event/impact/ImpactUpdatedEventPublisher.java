package com.evatool.impact.domain.event.impact;

import com.evatool.global.event.impact.ImpactUpdatedEvent;
import com.evatool.impact.application.json.mapper.ImpactJsonMapper;
import com.evatool.impact.domain.entity.Impact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ImpactUpdatedEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ImpactUpdatedEventPublisher.class);

    private ApplicationEventPublisher applicationEventPublisher;

    public ImpactUpdatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onImpactUpdated(final Impact impact) {
        logger.info("Preparing to publish event");
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactUpdatedEvent = new ImpactUpdatedEvent(this, impactJson.toString());
        applicationEventPublisher.publishEvent(impactUpdatedEvent);
        logger.info("Event published");
    }
}
