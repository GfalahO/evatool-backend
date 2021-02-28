package com.evatool.impact.domain.event;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.impact.ImpactDeletedEvent;
import com.evatool.global.event.impact.ImpactUpdatedEvent;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.event.json.mapper.ImpactJsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ImpactEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ImpactEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public ImpactEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishImpactCreated(final Impact impact) {
        logger.info("Preparing to publish create impact event");
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactCreatedEvent = new ImpactCreatedEvent(this, impactJson);
        applicationEventPublisher.publishEvent(impactCreatedEvent);
        logger.info("Create impact event published");
    }

    public void publishImpactDeleted(final Impact impact) {
        logger.info("Preparing to publish delete impact event");
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactDeletedEvent = new ImpactDeletedEvent(this, impactJson);
        applicationEventPublisher.publishEvent(impactDeletedEvent);
        logger.info("Delete impact event published");
    }

    public void publishImpactUpdated(final Impact impact) {
        logger.info("Preparing to publish update impact event");
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactUpdatedEvent = new ImpactUpdatedEvent(this, impactJson);
        applicationEventPublisher.publishEvent(impactUpdatedEvent);
        logger.info("Update impact event published");
    }
}
