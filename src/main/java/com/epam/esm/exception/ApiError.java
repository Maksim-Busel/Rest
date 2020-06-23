package com.epam.esm.exception;

public class ApiError {
    private String message;
    private String error;

    public ApiError(String message, String error) {
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
