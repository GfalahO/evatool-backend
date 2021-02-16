package com.evatool.global.event;

import org.springframework.context.ApplicationEvent;

public class RequirementCreated extends ApplicationEvent {

    private String message;

    public RequirementCreated(Object source, String message) {
        super(source);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
