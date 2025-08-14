package com.example.profileservice.dto;

public class AuthUserRequest {
    private String uniqueId; // MongoDB studentId
    private String email;    // official university email
    private String password;

    public AuthUserRequest() {}

    public AuthUserRequest(String uniqueId, String email, String password) {
        this.uniqueId = uniqueId;
        this.email = email;
        this.password = password;
    }

    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
