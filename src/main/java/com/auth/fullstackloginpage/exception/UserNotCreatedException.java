package com.auth.fullstackloginpage.exception;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
