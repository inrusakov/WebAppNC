package com.example.model;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword_encoded();
    }

    // Предпологается авторизация с помощью поля User.email
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // default = false
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // default = false
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // default = false
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // default = false
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getUserId(){
        return user.getId();
    }
    public String getUserFirstName() { return user.getFirstName(); }
    public String getUserLatsName() { return  user.getLastName(); }
    public String getUserFullName() { return getUserFirstName() + " " + getUserLatsName(); }
    public User getUser() { return this.user; }
//    public Address getUserAddress() {return user.getUserAddress();}
//    public Object getUserBlog(){return user.getBlog();}
//    public Object getUserTag(){return user.getTag();}
}
