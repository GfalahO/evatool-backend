package com.evatool.impact.domain.event;

import org.springframework.context.ApplicationEvent;

public class DummyEvent extends ApplicationEvent {
    public DummyEvent(Object source) {
        super(source);
    }
}
