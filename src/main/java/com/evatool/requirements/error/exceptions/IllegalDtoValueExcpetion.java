package com.evatool.requirements.error.exceptions;

import java.util.UUID;

public class IllegalDtoValueExcpetion extends IllegalArgumentException{

    public IllegalDtoValueExcpetion(Object value, String reason) {
        super(reason);
    }
}
