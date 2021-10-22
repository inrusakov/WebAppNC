package com.example.controller;

import com.example.model.User;
import com.example.model.blog.Comment;
import com.example.model.blog.Post;
import com.example.model.blog.PostComment;
import com.example.repos.PostCommentRepository;
import com.example.repos.PostRepository;
import com.example.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@AllArgsConstructor

@Controller // Веб-логика
public class CommentController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private UserRepository userRepository;


    // OBSERVING COMMENTS
    @GetMapping("/showComment/{id}")
    public String showComment(@PathVariable("id") Integer id, String submit, Model model) {
        Comment c = new Comment();
        User user = new User();
        user.setFirstName("John");
        c.setUser(user);
        c.setCommentBody("body");
        model.addAttribute("comment", c);
        model.addAttribute("user", user);
        return "comment";
    }

    @GetMapping("/likeComment/{postId}/{id}/{userId}")
    public RedirectView likeComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, Model model) {
        PostComment comment = postCommentRepository.getById(id);
        List<User> likes = comment.getLikes();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        if (!likes.contains(user))
            comment.putLike(user);
        else comment.removeLike(user);

        postCommentRepository.save(comment);
        return new RedirectView("/postObserver/{postId}");
    }

    @GetMapping("/levelUpComment/{postId}/{id}/{userId}")
    public RedirectView levelUpComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, Model model) {
        PostComment comment = postCommentRepository.getById(id);
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        List<User> up = comment.getUpVoters();
        List<User> down = comment.getDownVoters();

        if (down.contains(user)) {
            down.remove(user);
            comment.increaseRating(user);
        }else if(!up.contains(user)){
            up.add(user);
            comment.increaseRating(user);
        }

        postCommentRepository.save(comment);
        return new RedirectView("/postObserver/{postId}");
    }



        @GetMapping("/lowerComment/{postId}/{id}/{userId}")
        public RedirectView lowerComment (@PathVariable("id") Integer id, @PathVariable("postId") Integer
        postId, @PathVariable("userId") Integer userId, Model model){
            PostComment comment = postCommentRepository.getById(id);
            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

            List<User> up = comment.getUpVoters();
            List<User> down = comment.getDownVoters();

            if (up.contains(user)) {
                up.remove(user);
                comment.decreaseRating(user);
            }else if(!down.contains(user)){
                down.add(user);
                comment.decreaseRating(user);
            }

            postCommentRepository.save(comment);
            return new RedirectView("/postObserver/{postId}");
        }


        @GetMapping("/addComment/{postId}")
        public RedirectView addComment (@PathVariable("postId") Integer postId, @RequestParam String
        comm_body, @RequestParam Integer userId, RedirectAttributes attributes){
            PostComment comment = new PostComment();

            Post p = (Post) postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
            User u = (User) userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

            comment.setCommentBody(comm_body);
            comment.setCreationTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            comment.setUser(u);
            comment.setPost(p);

            postCommentRepository.save(comment);
            return new RedirectView("/postObserver/{postId}");
        }

        @GetMapping("/editComment/{postId}/{id}")
        public RedirectView editComment (@PathVariable("id") Integer id, @PathVariable("postId") Integer
        postId, @RequestParam(required = false) String edit_comment_body, @RequestParam(required = false) String
        editComment, RedirectAttributes attributes){
            PostComment comment = postCommentRepository.getById(id);
            Integer edit = comment.getEdit();
            if (edit == 1) {
                if (editComment.equals("Delete")) {
                    postCommentRepository.deleteById(id);
                } else {
                    comment.setCommentBody(edit_comment_body);
                    comment.setEdit(0);
                    postCommentRepository.save(comment);
                }
            } else {
                comment.setEdit(1);
                postCommentRepository.save(comment);
            }
            return new RedirectView("/postObserver/{postId}");
        }

    @GetMapping(value = "/reply/{userId}/{postId}/{commUserId}/{commentId}")

    public String replyToComment(Model model, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId,
                                 @PathVariable("commUserId") Integer commUserId, @PathVariable("commentId") Integer commentId) {
        model.addAttribute("user", userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId)));
        model.addAttribute("post", postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId)));
        model.addAttribute("commUser", userRepository.findById(commUserId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + commUserId)));
        model.addAttribute("rootComment", postCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + commentId)));
        PostComment c = new PostComment();
        c.setId(-1);
        model.addAttribute("comment", c);

        return "replyToComment :: form";
    }

    @RequestMapping(value = "/addReplyComment/{userId}/{postId}/{commUserId}/{commentId}")
    public RedirectView addReplyComment (@PathVariable("postId") Integer postId, @RequestParam String
            comm_body, @RequestParam String addComment,
            @PathVariable("userId") Integer userId, @PathVariable("commUserId") Integer commUserId,
            @PathVariable("commentId") Integer commentId,  RedirectAttributes attributes){

        if(addComment.equals("Submit")) {
            PostComment comment = new PostComment();

            PostComment rootComment = (PostComment) postCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + commentId));
            Post p = (Post) postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
            User u = (User) userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

            comment.setCommentBody(comm_body);
            comment.setCreationTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            comment.setUser(u);
            comment.setPost(p);
            int layer = rootComment.getLayer();
            comment.setLayer(layer+1);
            comment.setParentComment(rootComment);
            rootComment.addSubComment(comment);

            postCommentRepository.save(comment);
            postCommentRepository.save(rootComment);
        }
        return new RedirectView("/postObserver/{postId}");
    }
}

