package com.example.service;

import com.example.controller.CommentController;
import com.example.controller.UserController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class UserService {

    @Autowired
    private UserController userController;
}
