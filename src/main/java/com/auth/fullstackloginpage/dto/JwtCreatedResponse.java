package com.auth.fullstackloginpage.dto;

public class JwtCreatedResponse {

    private final String jwt;

    public JwtCreatedResponse(String jwt) {
        this.jwt = jwt;
    }
}