package com.epam.esm.exception;

public class DaoException extends RuntimeException {

    public DaoException() {}

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable e) {
        super(e);
    }
}
