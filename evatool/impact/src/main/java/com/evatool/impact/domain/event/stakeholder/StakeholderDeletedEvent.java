package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderDeletedEvent extends ApplicationEvent {
    @Getter
    private ImpactStakeholder stakeholder;

    public StakeholderDeletedEvent(Object source, ImpactStakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}

