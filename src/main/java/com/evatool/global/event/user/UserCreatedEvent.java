package com.evatool.global.event.user;

import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.dto.UserDTO;
import com.evatool.analysis.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    @Getter
    private String jsonPayload;

    public UserCreatedEvent(String jsonPayload) {
        super(jsonPayload);
        this.jsonPayload = jsonPayload;
    }
}
