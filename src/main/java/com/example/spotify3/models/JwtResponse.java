package com.example.spotify3.models;

public class JwtResponse {

    //login request which will then generate web token for us
    private String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getToken() {
        return this.jwt;
    }
}
