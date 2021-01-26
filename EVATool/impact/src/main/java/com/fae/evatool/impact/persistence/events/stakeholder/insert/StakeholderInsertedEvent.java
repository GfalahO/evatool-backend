package com.fae.evatool.impact.persistence.events.stakeholder.insert;

import com.fae.evatool.impact.persistence.entity.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderInsertedEvent extends ApplicationEvent {
    @Getter
    private Stakeholder stakeholder;

    public StakeholderInsertedEvent(Object source, Stakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}
