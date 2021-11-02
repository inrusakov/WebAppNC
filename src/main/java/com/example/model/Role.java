package com.example.model;

import org.springframework.security.core.GrantedAuthority;

//public enum Role implements GrantedAuthority {
public enum Role {
    USER, COMPANY, ADMIN;
/*
    @Override
    public String getAuthority() {
        return name();
    }
*/
}
