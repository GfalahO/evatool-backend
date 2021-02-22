package com.evatool.analysis.events;

import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisCreatedEvent extends ApplicationEvent {
    private AnalysisDTO dto;

    public AnalysisCreatedEvent(AnalysisDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public AnalysisDTO getValue(){
        return this.dto;
    }
}
