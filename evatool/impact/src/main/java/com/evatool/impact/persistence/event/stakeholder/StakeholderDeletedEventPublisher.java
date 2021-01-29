package com.evatool.impact.persistence.event.stakeholder;

import com.evatool.impact.persistence.entity.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderDeletedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void onStakeholderDeleted(final Stakeholder stakeholder) {
        var stakeholderDeletedEvent = new StakeholderDeletedEvent(this, stakeholder);
        applicationEventPublisher.publishEvent(stakeholderDeletedEvent);
    }
}
