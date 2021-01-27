package com.evatool.impact.persistence.event.incoming.stakeholder.update;

import com.evatool.impact.persistence.entity.Stakeholder;
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
