package com.evatool.impact.exception.handle;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

public class ErrorMessage {
    @Getter
    @Setter
    private Date timestamp;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String details;

    @Getter
    @Setter
    private String path;

    public ErrorMessage() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public ErrorMessage(String message) {
        this();
        this.message = message;
    }

    public ErrorMessage(String message, String details) {
        this(message);
        this.details = details;
    }

    public ErrorMessage(String message, String details, String path) {
        this(message, details);
        this.path = path;
    }
}
