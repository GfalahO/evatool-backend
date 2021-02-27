package com.evatool.impact.domain.event.dimension;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.impact.domain.event.json.mapper.DimensionJsonMapper;
import com.evatool.impact.domain.entity.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionCreatedEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DimensionCreatedEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public DimensionCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDimensionCreated(final Dimension dimension) {
        logger.info("Preparing to publish event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionCreatedEvent = new DimensionCreatedEvent(this, dimensionJson.toString());
        applicationEventPublisher.publishEvent(dimensionCreatedEvent);
        logger.info("Event published");
    }
}
