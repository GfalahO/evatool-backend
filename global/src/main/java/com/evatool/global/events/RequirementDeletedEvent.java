package com.evatool.global.events;

import org.springframework.context.ApplicationEvent;

public class RequirementDeletedEvent extends ApplicationEvent {
    private String requirement;

    public RequirementDeletedEvent( String requirement) {
        super(requirement);
        this.requirement=requirement;
    }
}
