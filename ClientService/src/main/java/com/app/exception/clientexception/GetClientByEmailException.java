package com.app.exception.clientexception;

public class GetClientByEmailException extends RuntimeException {
    public GetClientByEmailException(String message) {
        super(message);
    }
}
