package com.example.demo.exceptions;

public class NotNullException extends Exception{
    public NotNullException() {
        super();
    }

    public NotNullException(String message) {
        super(message);
    }

    public NotNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotNullException(Throwable cause) {
        super(cause);
    }

    protected NotNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
