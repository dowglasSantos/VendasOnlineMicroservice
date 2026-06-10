package com.app.exception.clientexception;

public class GetClientByIdException extends RuntimeException {
    public GetClientByIdException(String message) {
        super(message);
    }
}
