package com.example.controller;

import com.example.model.blog.Post;
import com.example.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;

    // ADDING NEW POST
    @GetMapping("/addPost")
    public String addPost(){
        return "post";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(@RequestParam String header, @RequestParam String content){
        Post post = new Post();
        post.setHeader(header);
        post.setContent(content);
        post.setPublicationDate(LocalDateTime.now());

        postRepository.save(post);
        return new RedirectView("/allPosts");
    }

    // OBSERVING POST
    @GetMapping("/postObserver/{postId}")
    public String observePost(@PathVariable("postId") Integer postId, String submit, Model model){
        model.addAttribute("post", (Post)postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId)));
        return "postObserver";
    }

    // DELETING POST
    @GetMapping("/deletePost/{postId}")
    public RedirectView deletePost(@PathVariable("postId") Integer postId){
        postRepository.deleteById(postId);
        return new RedirectView("/allPosts");
    }

    // EDITING POST
    @GetMapping("/editPost/{postId}")
    public String editPost(@PathVariable("postId") Integer postId, Model model){
        model.addAttribute("post", (Post)postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId)));
        return "editPost";
    }

    @PostMapping("/editPost/{postId}")
    public RedirectView editPost(@PathVariable("postId") Integer postId, @ModelAttribute Post post){
        post.setPublicationDate(LocalDateTime.now());
        postRepository.save(post);
        return new RedirectView("/postObserver/{postId}");
    }

    // SHOWING ALL POSTS
    @GetMapping("/allPosts")
    public String getAllPosts(Model model){
        List<Post> posts = new ArrayList<>();
        Iterable<Post> it = postRepository.findAll();
        it.forEach(posts::add);
        model.addAttribute("posts", posts);
        return "blogPage";
    }
}
