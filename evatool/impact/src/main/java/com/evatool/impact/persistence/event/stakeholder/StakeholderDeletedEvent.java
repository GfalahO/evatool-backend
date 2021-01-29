package com.evatool.impact.persistence.event.stakeholder;

import com.evatool.impact.persistence.entity.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderDeletedEvent extends ApplicationEvent {
    @Getter
    private Stakeholder stakeholder;

    public StakeholderDeletedEvent(Object source, Stakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}

