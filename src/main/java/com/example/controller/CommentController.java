package com.example.controller;

import com.example.repos.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@AllArgsConstructor

@Controller // Веб-логика
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

}
