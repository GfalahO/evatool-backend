package com.evatool.analysis.events;

import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.dto.UserDTO;
import com.evatool.analysis.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    private UserDTO dto;

    public UserCreatedEvent(UserDTO dto) {
        super(dto);
        this.dto = dto;
    }

    public UserDTO getValue(){
        return this.dto;
    }
}
