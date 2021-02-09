package com.evatool.requirements.events;

import com.evatool.requirements.dto.RequirementDTO;
import org.springframework.context.ApplicationEvent;

public class RequirementCreatedEvent extends ApplicationEvent {
    private RequirementDTO dto;

    public RequirementCreatedEvent(RequirementDTO dto) {
        super(dto);
        this.dto = dto;
    }
}
