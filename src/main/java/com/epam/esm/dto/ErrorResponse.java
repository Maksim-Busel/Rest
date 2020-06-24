package com.epam.esm.dto;

public class ErrorResponse {
    private String message;
    private String error;

    public ErrorResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrors() {
        return error;
    }

    public void setErrors(String error) {
        this.error = error;
    }
}
