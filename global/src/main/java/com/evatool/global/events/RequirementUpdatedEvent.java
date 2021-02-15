package com.evatool.global.events;


import org.springframework.context.ApplicationEvent;

public class RequirementUpdatedEvent extends ApplicationEvent {
    private String requirement;

    public RequirementUpdatedEvent(String dto) {
        super(dto);
        this.requirement = dto;
    }
}
