package com.auth.fullstackloginpage.exception;

public class InvalidJWTAuthenticationException extends RuntimeException {

    public InvalidJWTAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}