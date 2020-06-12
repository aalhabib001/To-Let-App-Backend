package com.toletproject.ToLetProject.jwt.dto.response;

import java.util.Set;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private Set<String> roles;

    public JwtResponse(String accessToken, Set<String> roleResponses) {
        this.token = accessToken;
        roles = roleResponses;
    }

    public Set<String> getRole() {
        return roles;
    }

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    public void setRole(Set<String> role) {
        this.roles = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}