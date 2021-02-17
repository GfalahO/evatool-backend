package com.evatool.impact.domain.event.impact;

import com.evatool.global.event.impact.ImpactDeletedEvent;
import com.evatool.impact.application.json.mapper.ImpactJsonMapper;
import com.evatool.impact.domain.entity.Impact;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ImpactDeletedEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public ImpactDeletedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onImpactDeleted(final Impact impact) {
        var impactJson = ImpactJsonMapper.toJson(impact);
        var impactDeletedEvent = new ImpactDeletedEvent(this, impactJson.toString());
        applicationEventPublisher.publishEvent(impactDeletedEvent);
    }
}
