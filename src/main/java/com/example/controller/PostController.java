package com.example.controller;

import com.example.model.blog.Post;
import com.example.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public RedirectView addPost(@RequestParam String header, @RequestParam String content, @RequestParam("file") MultipartFile file, RedirectAttributes attributes){
        Post post = new Post();
        post.setHeader(header);
        post.setContent(content);
        post.setPublicationDate(LocalDateTime.now());

        MultipartFile f = file;
        System.out.println(f.getContentType());

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
    public String getAllPosts(Model model, @PageableDefault(sort = {"postId"}, direction = Sort.Direction.DESC) Pageable pageable){
        //List<Post> posts = new ArrayList<>();
        Page<Post> page = postRepository.findAll(pageable);
        //it.forEach(posts::add);
        model.addAttribute("page", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            List<Integer> elemNumbers = Arrays.asList(5, 10, 15, 20);
            model.addAttribute("elemNumbers", elemNumbers);
            model.addAttribute("num", pageable.getPageNumber());
        }
        return "blogPage";
    }
}
