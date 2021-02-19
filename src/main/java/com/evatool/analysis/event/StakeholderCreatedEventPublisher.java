package com.evatool.analysis.event;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StakeholderCreatedEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public StakeholderCreatedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void onStakeholderCreated() {
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", UUID.randomUUID().toString(), "ich komme aus analysis");
        var stakeholderCreatedEvent = new StakeholderCreatedEvent(this, json);
        applicationEventPublisher.publishEvent(stakeholderCreatedEvent);
    }
}
