package com.example.service;

import com.example.controller.CommentController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service // Бизнес-логика
public class CommentService {

    @Autowired
    private CommentController commentController;
}
