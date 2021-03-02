package com.evatool.global.event.analysis;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisCreatedEvent extends ApplicationEvent {

    @Getter
    private String jsonPayload;

//    public AnalysisCreatedEvent(Object source, String jsonPayload) {
//        super(source);
//        this.jsonPayload = jsonPayload;
//    }

    public AnalysisCreatedEvent(String jsonPayload) {
        super(jsonPayload);
        this.jsonPayload = jsonPayload;
    }
}
