package com.fae.evatool.impact.persistence.event.stakeholder.update;

import com.fae.evatool.impact.persistence.entity.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderUpdatedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void onStakeholderUpdated(final Stakeholder stakeholder) {
        var stakeholderUpdatedEvent = new StakeholderUpdatedEvent(this, stakeholder);
        applicationEventPublisher.publishEvent(stakeholderUpdatedEvent);
    }
}
