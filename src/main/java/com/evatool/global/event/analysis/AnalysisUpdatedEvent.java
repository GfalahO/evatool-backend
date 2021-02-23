package com.evatool.global.event.analysis;

import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisUpdatedEvent extends ApplicationEvent {
    private AnalysisDTO dto;

    public AnalysisUpdatedEvent(AnalysisDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public AnalysisDTO getValue(){
        return this.dto;
    }
}
