package com.fae.evatool.impact.persistence.event;

import com.fae.evatool.impact.persistence.entity.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderInsertPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void onStakeholderInserted(final Stakeholder stakeholder) {
        var stakeholderInsertEvent = new StakeholderInsertEvent(this, stakeholder);
        applicationEventPublisher.publishEvent(stakeholderInsertEvent);
    }
}