package com.evatool.impact.domain.event.impact;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactJsonMapper;
import com.evatool.impact.domain.entity.Impact;
import org.springframework.context.ApplicationEventPublisher;

public class ImpactCreatedEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public ImpactCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onImpactCreated(final Impact impact) {
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactCreatedEvent = new ImpactCreatedEvent(this, impactJson.toString());
        applicationEventPublisher.publishEvent(impactCreatedEvent);
    }
}
