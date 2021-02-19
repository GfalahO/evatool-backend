package com.evatool.impact.common.exception.handle;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

public class ErrorMessage {
    @Getter
    private final Date timestamp;

    @Getter
    private final String message;

    @Getter
    private final String uri;

    public ErrorMessage(String message, String uri) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.message = message;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
