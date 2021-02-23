package com.evatool.requirements.error.exceptions;

public class IllegalDtoValueExcpetion extends IllegalArgumentException{

    public IllegalDtoValueExcpetion(Object value, String reason) {
        super(reason);
    }
}
