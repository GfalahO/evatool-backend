package com.evatool.impact.persistence.event.stakeholder.insert;

import com.evatool.impact.persistence.entity.Stakeholder;
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
