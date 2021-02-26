package com.evatool.requirements.error.exceptions;

import com.evatool.requirements.entity.Requirement;

public class IllegalDtoValueExcpetion extends IllegalArgumentException{

    private Requirement requirement;

    public IllegalDtoValueExcpetion(Requirement requirement, String reason) {
        super(reason);
        this.requirement=requirement;
    }

    public Requirement getRequirement() {
        return requirement;
    }
}
