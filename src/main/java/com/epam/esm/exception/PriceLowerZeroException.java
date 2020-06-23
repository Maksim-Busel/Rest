package com.epam.esm.exception;

public class PriceLowerZeroException extends RuntimeException{

    public PriceLowerZeroException() {}

    public PriceLowerZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public PriceLowerZeroException(String message) {
        super(message);
    }

    public PriceLowerZeroException(Throwable e) {
        super(e);
    }
}
