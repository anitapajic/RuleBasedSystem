package com.ftn.sbnz.model.dto.user;

import com.ftn.sbnz.model.models.user.Role;

public class UserTokenState {

    private String accessToken;
    private Long expiresIn;
    private String role;
    private Integer userId;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.role = null;
        this.userId = null;
    }

    public UserTokenState(String accessToken, long expiresIn, String role, Integer userId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
