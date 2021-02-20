package com.evatool.impact.common.exception.handle;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;

public class ErrorMessage {
    @Getter
    private final Date timestamp;

    @Getter
    private final int status;

    @Getter
    private final String error;

    @Getter
    private final String trace;

    @Getter
    private final String message;

    @Getter
    private final String path;

    public ErrorMessage(Exception exception, String message, String path, HttpStatus httpStatus) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = httpStatus.value();
        this.error = httpStatus.toString();
        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        this.trace = sw.toString();
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", uri='" + path + '\'' +
                '}';
    }
}
