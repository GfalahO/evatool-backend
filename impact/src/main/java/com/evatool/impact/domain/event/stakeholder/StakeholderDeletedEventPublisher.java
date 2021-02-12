package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StakeholderDeletedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void onStakeholderDeleted(final ImpactStakeholder stakeholder)  {
        var stakeholderDeletedEvent = new StakeholderDeletedEvent(this, stakeholder);
        applicationEventPublisher.publishEvent(stakeholderDeletedEvent);
    }
}
