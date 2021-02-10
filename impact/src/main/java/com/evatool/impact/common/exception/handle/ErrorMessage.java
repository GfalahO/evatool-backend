package com.evatool.impact.common.exception.handle;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

public class ErrorMessage {
    @Getter
    private final Date timestamp;

    @Getter
    private final String message;

    public ErrorMessage(String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
