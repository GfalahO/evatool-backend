package com.evatool.global.event.user;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class UserDeletedEvent extends ApplicationEvent {

    @Getter
    private String jsonPayload;

    public UserDeletedEvent(Object source, String jsonPayload) {
        super(source);
        this.jsonPayload = jsonPayload;
    }
}
