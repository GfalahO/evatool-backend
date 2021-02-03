package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.entity.Stakeholder;
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
