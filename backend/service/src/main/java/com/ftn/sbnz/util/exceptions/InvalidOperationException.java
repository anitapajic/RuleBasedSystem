package com.ftn.sbnz.util.exceptions;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException() {
    }
}
