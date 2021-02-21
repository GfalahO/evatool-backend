package com.evatool.impact.domain.event.dimension;

import com.evatool.global.event.dimension.DimensionDeletedEvent;
import com.evatool.impact.application.json.mapper.DimensionJsonMapper;
import com.evatool.impact.domain.entity.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionDeletedEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DimensionDeletedEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public DimensionDeletedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDimensionDeleted(final Dimension dimension) {
        logger.info("Preparing to publish event");
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionDeletedEvent = new DimensionDeletedEvent(this, dimensionJson.toString());
        applicationEventPublisher.publishEvent(dimensionDeletedEvent);
        logger.info("Event published");
    }
}
