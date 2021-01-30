package com.evatool.impact.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StakeholderNotFoundException extends Exception {
    public StakeholderNotFoundException(String message) {
        super(message);
    }
}
