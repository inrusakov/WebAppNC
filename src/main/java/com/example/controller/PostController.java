package com.example.controller;

import com.example.model.User;
import com.example.model.blog.Blog;
import com.example.model.blog.Post;
import com.example.model.blog.PostComment;
import com.example.model.response.Response;
import com.example.repos.BlogRepository;
import com.example.repos.PostCommentRepository;
import com.example.repos.PostRepository;
import com.example.service.AuthenticationService;
import com.example.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/post")
public class PostController{
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private EnvUtil envUtil;

    // REST GET
    @GetMapping("{postId}")
    public EntityModel<Post> observePost(@PathVariable("postId") Integer postId){
        Post post = (Post) postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        Blog blog = (Blog) blogRepository.findById(post.getBlog().getBlogID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        //List<Post> posts = postRepository.findAllByBlog(blog);
        //return posts;
        return EntityModel.of(post,
                linkTo(methodOn(BlogController.class).getPosts(blog.getBlogID())).withRel("blog"),
                linkTo(methodOn(BlogController.class).getAllPosts(blog.getBlogID())).withRel("posts"));
    }

    // REST POST
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post){
        User author = AuthenticationService.getCurrentUser();

        post.setPublicationDate(LocalDateTime.now());
        post.setViews(0);
        post.setRating(0);
        post.setArchived(false);
        post.setBlog(author.getBlog());

        Post newPost = postRepository.save(post);

        EntityModel<Post> entityModel = EntityModel.of(newPost,
                linkTo(methodOn(PostController.class).observePost(newPost.getPostId())).withSelfRel(),
                //linkTo(methodOn(CommentController.class).getComments()).withRel("postcomments"), // TO DO : MODIFY COMMENTS [connection with post]
                linkTo(methodOn(BlogController.class).getPosts(newPost.getBlog().getBlogID())).withRel("blog"));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // REST UPDATE
    @PutMapping("{id}")
    public ResponseEntity<?> updatePost(@RequestBody Post newPost){
        Post post = postRepository.findById(newPost.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment id:" + newPost.getPostId()));

        User author = AuthenticationService.getCurrentUser();
        post.setHeader(newPost.getHeader());
        post.setContent(newPost.getContent());
        post.setRating(newPost.getRating());
        post.setViews(newPost.getViews());
        post.setPublicationDate(LocalDateTime.now());
        post.setBlog(author.getBlog());
        postRepository.save(post);

        EntityModel<Post> entityModel = EntityModel.of(post,
                linkTo(methodOn(PostController.class).observePost(post.getPostId())).withSelfRel(),
                //linkTo(methodOn(CommentController.class).getComments()).withRel("postcomments"),
                linkTo(methodOn(BlogController.class).getPosts(newPost.getBlog().getBlogID())).withRel("blog"));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // REST DELETE
    @DeleteMapping("{postId}")
    public ResponseEntity<?> removePost(@PathVariable("postId") Integer postId){
       // postRepository.deleteById(postId);
        Post post = (Post)postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        post.setArchived(true);
        postRepository.save(post);
        return ResponseEntity.noContent().build();
    }

    // ADDING NEW POST
    @GetMapping("/addPost")
    public String addPost(){
        return "post";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(@RequestParam String header, @RequestParam String content, @RequestParam(name = "file", required = false) MultipartFile file,
                                RedirectAttributes attributes){
        User author = AuthenticationService.getCurrentUser();
        Post post = new Post();
        post.setHeader(header);
        post.setContent(content);
        post.setPublicationDate(LocalDateTime.now());
        post.setBlog(author.getBlog());

        MultipartFile f = file;

        postRepository.save(post);
        return new RedirectView("/allBlogs");
    }

    // OBSERVING POST
    @GetMapping("/postObserver/{postId}")
    public String observePost(@PathVariable("postId") Integer postId, Model model) throws UnknownHostException {
        User mainUser = AuthenticationService.getCurrentUser();

        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());

        model.addAttribute("mainUser", mainUser);

        PostComment c = new PostComment();
        c.setId(0);
        model.addAttribute("newComment", c);

        List<PostComment> comments = postCommentRepository.findAllRootComments(postId); // найти все комментарии 0-го уровня
        comments.sort(Comparator.comparing(PostComment::getTotalRating));
        Collections.reverse(comments);
        model.addAttribute("comments", comments);

        Post post = (Post)postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        model.addAttribute("post", post);
        model.addAttribute("author", post.getAuthor());
        return "postObserver";
    }

    // DELETING POST
    @GetMapping("/deletePost/{postId}")
    public RedirectView deletePost(@PathVariable("postId") Integer postId){
        Post post = (Post)postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        post.setArchived(true);
        postRepository.save(post);
        //postRepository.deleteById(postId);    // архивируем вместо удаления
        return new RedirectView("/allBlogs");
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
        User author = AuthenticationService.getCurrentUser();
        post.setPublicationDate(LocalDateTime.now());
        post.setBlog(author.getBlog());
        postRepository.save(post);
        return new RedirectView("/postObserver/{postId}");
    }
}
