package com.money.changing.controllers.exceptions;

public class BadEntryInputException extends Exception {

    private static final long serialVersionUID = 1L;

    public BadEntryInputException(String message) {
        super(message);
    }

    public BadEntryInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
