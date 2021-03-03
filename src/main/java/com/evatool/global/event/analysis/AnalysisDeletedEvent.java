package com.evatool.global.event.analysis;

import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisDeletedEvent extends ApplicationEvent {

    @Getter
    private String jsonPayload;

    public AnalysisDeletedEvent(String jsonPayload) {
        super(jsonPayload);
        this.jsonPayload = jsonPayload;
    }
}
