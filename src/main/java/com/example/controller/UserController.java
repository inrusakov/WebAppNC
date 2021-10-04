package com.example.controller;

import com.example.repos.CommentRepository;
import com.example.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@AllArgsConstructor

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

}
