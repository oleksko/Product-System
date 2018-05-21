package com.app.model.exceptions;

import java.time.LocalDateTime;

public class MyException extends RuntimeException {
    private String errorMessage;
    private LocalDateTime dateTime;

    public MyException(String errorMessage, LocalDateTime dateTime) {
        this.errorMessage = errorMessage;
        this.dateTime = dateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getMessage() {
        return "[ERROR]: " + errorMessage + " " + dateTime;
    }
}
