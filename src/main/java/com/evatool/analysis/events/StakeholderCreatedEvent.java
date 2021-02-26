package com.evatool.analysis.events;

import com.evatool.analysis.dto.StakeholderDTO;
import org.springframework.context.ApplicationEvent;

public class StakeholderCreatedEvent extends ApplicationEvent {
    private StakeholderDTO dto;

    public StakeholderCreatedEvent(StakeholderDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public StakeholderDTO getValue(){
        return this.dto;
    }
}
