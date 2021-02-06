package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderInsertedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void onStakeholderInserted(final ImpactStakeholder stakeholder) {
        var stakeholderInsertEvent = new StakeholderInsertedEvent(this, stakeholder);
        applicationEventPublisher.publishEvent(stakeholderInsertEvent);
    }
}
