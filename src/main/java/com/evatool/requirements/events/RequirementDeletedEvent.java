package com.evatool.requirements.events;

import com.evatool.requirements.dto.RequirementDTO;
import org.springframework.context.ApplicationEvent;

public class RequirementDeletedEvent extends ApplicationEvent {
    private RequirementDTO dto;

    public RequirementDeletedEvent(RequirementDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public RequirementDTO getValue(){
        return this.dto;
    }
}
