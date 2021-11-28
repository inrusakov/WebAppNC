package com.example.controller;

import com.example.model.User;
import com.example.model.blog.Blog;
import com.example.model.blog.Post;
import com.example.repos.BlogRepository;
import com.example.repos.PostRepository;
import com.example.service.AuthenticationService;
import com.example.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EnvUtil envUtil;

    @GetMapping("/blog/{blogId}")
    public EntityModel<Blog> getPosts(@PathVariable("blogId") Integer blogId){
        Blog blog = (Blog) blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
        List<Post> posts = postRepository.findAllByBlog(blog);
        //return posts;
        return EntityModel.of(blog,
                linkTo(methodOn(BlogController.class).getPosts(blogId)).withSelfRel(),
                linkTo(methodOn(BlogController.class).getAllPosts(blogId)).withRel("posts"),
                linkTo(methodOn(BlogController.class).getAllBlogs()).withRel("blogs"));
    }
//
//     SHOWING USER POSTS
//    @GetMapping("/blog/{blogId}")
//    public String getPosts(@PathVariable("blogId") Integer blogId, Model model, @PageableDefault(sort = {"postId"}, direction = Sort.Direction.DESC) Pageable pageable) throws UnknownHostException {
//        User mainUser = AuthenticationService.getCurrentUser();
//
//        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
//        model.addAttribute("mainUser", mainUser);
//        Blog blog = (Blog) blogRepository.findById(blogId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
//        model.addAttribute("author", blog.getUser());
//        Page<Post> page = postRepository.findAllByBlog(blog, pageable);
//        model.addAttribute("page", page);
//
//        int totalPages = page.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//            List<Integer> elemNumbers = Arrays.asList(5, 10, 15, 20);
//            model.addAttribute("elemNumbers", elemNumbers);
//            model.addAttribute("num", pageable.getPageNumber());
//        }
//        return "blogPage";
//    }

    // SHOWING ALL POSTS
    @GetMapping("/allPosts")
    public String getAllPosts(Model model, @PageableDefault(sort = {"postId"}, direction = Sort.Direction.DESC) Pageable pageable){
        User mainUser = AuthenticationService.getCurrentUser();
        model.addAttribute("mainUser", mainUser);
        model.addAttribute("author", null);

        Page<Post> page = postRepository.findAll(pageable);
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

    @GetMapping("/blog/{blogId}/posts")
    public CollectionModel<EntityModel<Post>> getAllPosts(@PathVariable("blogId") Integer blogId){
        Blog blog = (Blog) blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + blogId));
        List<EntityModel<Post>> posts = postRepository.findAllByBlog(blog).stream()
                .map(post -> EntityModel.of(
                        post,
                        linkTo(methodOn(PostController.class).observePost(post.getPostId())).withRel("post")
                )).collect(Collectors.toList());

        return CollectionModel.of(
                posts,
                linkTo(methodOn(BlogController.class).getAllPosts(blogId)).withSelfRel(),
                linkTo(methodOn(BlogController.class).getPosts(blog.getBlogID())).withRel("blog"),
                linkTo(methodOn(BlogController.class).getAllBlogs()).withRel("blogs"));
    }

    // SHOWING ALL BLOGS
//    @GetMapping("/allBlogs")
//    public String getAllBlogs(Model model, @PageableDefault(sort = {"blogID"}, direction = Sort.Direction.DESC) Pageable pageable){
//        Page<Blog> page = blogRepository.findAll(pageable);
//        model.addAttribute("page", page);
//
//        int totalPages = page.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//            List<Integer> elemNumbers = Arrays.asList(5, 10, 15, 20);
//            model.addAttribute("elemNumbers", elemNumbers);
//            model.addAttribute("num", pageable.getPageNumber());
//        }
//        return "blogList";
//    }

    @GetMapping
    public CollectionModel<EntityModel<Blog>> getAllBlogs(){
        List<EntityModel<Blog>> blogs = blogRepository.findAll().stream()
                .map(blog -> EntityModel.of(
                        blog,
                        linkTo(methodOn(BlogController.class).getPosts(blog.getBlogID())).withSelfRel(),
                        linkTo(methodOn(BlogController.class).getAllBlogs()).withRel("blogs")
                )).collect(Collectors.toList());

        return CollectionModel.of(
                blogs,
                linkTo(methodOn(BlogController.class).getAllBlogs()).withSelfRel());
    }
}
