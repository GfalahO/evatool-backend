package com.evatool.impact.domain.event.dimension;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.impact.application.json.mapper.DimensionJsonMapper;
import com.evatool.impact.domain.entity.Dimension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionCreatedEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public DimensionCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDimensionCreated(final Dimension dimension) {
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionCreatedEvent = new DimensionCreatedEvent(this, dimensionJson.toString());
        applicationEventPublisher.publishEvent(dimensionCreatedEvent);
    }
}
