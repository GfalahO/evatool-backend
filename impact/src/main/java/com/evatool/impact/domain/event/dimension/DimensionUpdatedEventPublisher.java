package com.evatool.impact.domain.event.dimension;

import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.impact.application.json.mapper.DimensionJsonMapper;
import com.evatool.impact.domain.entity.Dimension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DimensionUpdatedEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public DimensionUpdatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onDimensionUpdated(final Dimension dimension) {
        var dimensionJson = DimensionJsonMapper.toJson(dimension);
        var dimensionUpdatedEvent = new DimensionUpdatedEvent(this, dimensionJson.toString());
        applicationEventPublisher.publishEvent(dimensionUpdatedEvent);
    }
}
