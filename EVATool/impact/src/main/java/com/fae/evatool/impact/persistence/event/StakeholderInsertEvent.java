package com.fae.evatool.impact.persistence.event;

import com.fae.evatool.impact.persistence.entity.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderInsertEvent extends ApplicationEvent {
    @Getter
    private Stakeholder stakeholder;

    public StakeholderInsertEvent(Object source, Stakeholder stakeholder) {
        super(source);
        this.stakeholder = stakeholder;
    }
}
