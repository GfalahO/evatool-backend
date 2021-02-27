package com.evatool.impact.domain.event.dimension;

import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.impact.application.json.mapper.DimensionJsonMapper;
import com.evatool.impact.domain.entity.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionUpdatedEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DimensionUpdatedEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public DimensionUpdatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDimensionUpdated(final Dimension dimension) {
        logger.info("Preparing to publish event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionUpdatedEvent = new DimensionUpdatedEvent(this, dimensionJson.toString());
        applicationEventPublisher.publishEvent(dimensionUpdatedEvent);
        logger.info("Event published");
    }
}
