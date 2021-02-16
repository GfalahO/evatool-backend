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

    public void onStakeholderCreated() throws InterruptedException {
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", UUID.randomUUID().toString(), "ich komme aus analysis");

        var stakeholderCreatedEvent = new StakeholderCreatedEvent(this, json);
        applicationEventPublisher.publishEvent(stakeholderCreatedEvent);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println("PUBLISHER");
        }
    }
}
