package com.evatool.analysis.event;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderCreatedEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public StakeholderCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onStakeholderCreated() {
        var stakeholderCreatedEvent = new StakeholderCreatedEvent(this, "Test Payload");
        applicationEventPublisher.publishEvent(stakeholderCreatedEvent);
    }
}
