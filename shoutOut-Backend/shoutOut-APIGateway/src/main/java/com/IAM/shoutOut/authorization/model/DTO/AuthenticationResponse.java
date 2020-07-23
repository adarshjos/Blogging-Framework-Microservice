package com.IAM.shoutOut.authorization.model.DTO;

public class AuthenticationResponse {
    private String authenticationToken;

    private String email;

    public AuthenticationResponse(String authenticationToken, String email) {
        this.authenticationToken = authenticationToken;
        this.email = email;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }
}
