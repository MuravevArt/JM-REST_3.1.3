package com.example.jmspringboot.exceptionHandling;

public class NoSuchUsersException extends RuntimeException {
    public NoSuchUsersException(String message) {
        super(message);
    }
}
