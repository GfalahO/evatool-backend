package com.evatool.global.event.stakeholder;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class StakeholderDeletedEvent extends ApplicationEvent {

    @Getter
    private String jsonPayload;

    public StakeholderDeletedEvent(Object source, String jsonPayload) {
        super(source);
        this.jsonPayload = jsonPayload;
    }
}
