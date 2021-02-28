package com.evatool.impact.domain.event;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.global.event.dimension.DimensionDeletedEvent;
import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.event.json.mapper.DimensionJsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DimensionEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public DimensionEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishDimensionCreated(final Dimension dimension) {
        logger.info("Preparing to publish create dimension event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionCreatedEvent = new DimensionCreatedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionCreatedEvent);
        logger.info("Create dimension event published");
    }

    public void publishDimensionDeleted(final Dimension dimension) {
        logger.info("Preparing to publish delete dimension event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionDeletedEvent = new DimensionDeletedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionDeletedEvent);
        logger.info("Delete dimension event published");
    }

    public void publishDimensionUpdated(final Dimension dimension) {
        logger.info("Preparing to publish update dimension event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionUpdatedEvent = new DimensionUpdatedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionUpdatedEvent);
        logger.info("Update dimension event published");
    }
}
