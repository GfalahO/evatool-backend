package com.fae.evatool.impact.persistence.event.stakeholder.update;

import com.fae.evatool.impact.persistence.entity.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderUpdatedEvent extends ApplicationEvent {
    @Getter
    private Stakeholder stakeholder;

    public StakeholderUpdatedEvent(Object source, Stakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}
