package com.evatool.global.event.analysis;

import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisCreatedEvent extends ApplicationEvent {

    private String msg;

    public AnalysisCreatedEvent(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage(){
        return this.msg;
    }
}
