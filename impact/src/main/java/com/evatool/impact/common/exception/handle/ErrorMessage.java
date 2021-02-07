package com.evatool.impact.common.exception.handle;

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
