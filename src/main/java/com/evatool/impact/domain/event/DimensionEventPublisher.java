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

    public void onDimensionCreated(final Dimension dimension) {
        logger.info("Preparing to publish create event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionCreatedEvent = new DimensionCreatedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionCreatedEvent);
        logger.info("Create event published");
    }

    public void onDimensionDeleted(final Dimension dimension) {
        logger.info("Preparing to publish delete event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionDeletedEvent = new DimensionDeletedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionDeletedEvent);
        logger.info("Delete event published");
    }

    public void onDimensionUpdated(final Dimension dimension) {
        logger.info("Preparing to publish update event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionUpdatedEvent = new DimensionUpdatedEvent(this, dimensionJson);
        applicationEventPublisher.publishEvent(dimensionUpdatedEvent);
        logger.info("Update event published");
    }
}
