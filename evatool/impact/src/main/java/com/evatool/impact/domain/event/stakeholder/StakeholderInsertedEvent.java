package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderInsertedEvent extends ApplicationEvent {
    @Getter
    private ImpactStakeholder stakeholder;

    public StakeholderInsertedEvent(Object source, ImpactStakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}
