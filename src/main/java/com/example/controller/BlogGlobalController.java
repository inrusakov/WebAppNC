package com.example.controller;

import com.example.model.blog.Blog;
import com.example.model.blog.Post;
import com.example.repos.BlogRepository;
import com.example.repos.PostCommentRepository;
import com.example.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Controller
public class BlogGlobalController {
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;


    @GetMapping("/index")
    public String getIndex(Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("messages", postCommentRepository.findAll());

        model.addAttribute("frontendData", data);
        return "index";
    }

    @GetMapping("/bloglist")
    public String getBlogs(Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("blogs", blogRepository.findAll());
        data.put("posts", postRepository.findAll());
        model.addAttribute("frontendData", data);
        return "blog/blogList";
    }

    @GetMapping("/blogpage/{blogId}")
    public String getBlog(@PathVariable Integer blogId, Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("blogs", blogRepository.findAll());
        Blog blog = (Blog) blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
        data.put("blog", blog);
        data.put("posts", postRepository.findAllByBlog(blog));
        data.put("post", null);

        model.addAttribute("frontendData", data);
        return "blog/blogPage";
    }

    @GetMapping("/blogpage/{blogId}/post/{postId}")
    public String getPost(@PathVariable Integer blogId, @PathVariable Integer postId, Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        Blog blog = (Blog) blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
        data.put("blog", blog);
        Post post = (Post) postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        data.put("post", post);
        //
        data.put("postcomments", null);

        model.addAttribute("frontendData", data);
        return "blog/postObserver";
    }

    @GetMapping("/blogpage/{blogId}/addpost")
    public String addPost(@PathVariable Integer blogId,Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("blogs", blogRepository.findAll());
        Blog blog = (Blog) blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
        data.put("blog", blog);
        data.put("posts", postRepository.findAllByBlog(blog));
        Post post = new Post();
        post.setBlog(blog);
        data.put("post", post);

        model.addAttribute("frontendData", data);
        return "blog/addPost";
    }
}
