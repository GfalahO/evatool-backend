package com.evatool.global.events;

import org.springframework.context.ApplicationEvent;

public class RequirementCreatedEvent extends ApplicationEvent {
    private String requirement;

    public RequirementCreatedEvent(String requirement) {
        super(requirement);
        this.requirement = requirement;
    }
}
