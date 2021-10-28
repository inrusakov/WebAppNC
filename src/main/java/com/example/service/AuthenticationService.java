package com.example.service;

import com.example.model.CustomUserDetails;
import com.example.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    /**
     * Выполняет проверку авторизованности пользователя
     * @return true - если авторизован, false - если не авторизован.
     */
    public static boolean isAuthenticated(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof CustomUserDetails);
    }

    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserDetails){
            return ((CustomUserDetails)principal).getUser();
        }
        else return null;
    }
}
