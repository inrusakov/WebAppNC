package com.example.controller;

import com.example.model.User;
import com.example.model.blog.Comment;
import com.example.model.blog.Post;
import com.example.model.blog.PostComment;
import com.example.repos.PostCommentRepository;
import com.example.repos.PostRepository;
import com.example.repos.UserRepository;
import com.example.util.EnvUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor

@Controller // Веб-логика
public class CommentController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnvUtil envUtil;

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

    @CrossOrigin("http://localhost:5000")
    @GetMapping("/likeComment/{postId}/{id}/{userId}")
    public ModelAndView likeComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, Model model) throws UnknownHostException {
        PostComment comment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Set<User> likes = comment.getLikes();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        if (!likes.contains(user))
            comment.putLike(user);
        else comment.removeLike(user);

        postCommentRepository.save(comment);
        //return new RedirectView("/postObserver/{postId}");
        model.addAttribute("comment", comment);
        model.addAttribute("user", comment.getUser());
        model.addAttribute("post", comment.getPost());
        model.addAttribute("mainUser", user);
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
        return new ModelAndView("comment.html :: div.fragment");
    }

    @CrossOrigin("http://localhost:5000")
    @GetMapping("/levelUpComment/{postId}/{id}/{userId}")
    public ModelAndView levelUpComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, Model model) throws UnknownHostException {
        PostComment comment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        Set<User> up = comment.getUpVoters();
        Set<User> down = comment.getDownVoters();

        if (down.contains(user)) {
            down.remove(user);
            comment.increaseRating(user);
        }else if(!up.contains(user)){
            up.add(user);
            comment.increaseRating(user);
        }

        postCommentRepository.save(comment);
        //return new RedirectView("/postObserver/{postId}");
        model.addAttribute("comment", comment);
        model.addAttribute("user", comment.getUser());
        model.addAttribute("post", comment.getPost());
        model.addAttribute("mainUser", user);
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
        return new ModelAndView("comment.html :: div.fragment");
    }


    @CrossOrigin("http://localhost:5000")
    @GetMapping("/lowerComment/{postId}/{id}/{userId}")
    public ModelAndView lowerComment (@PathVariable("id") Integer id, @PathVariable("postId") Integer
            postId, @PathVariable("userId") Integer userId, Model model) throws UnknownHostException {
        PostComment comment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        Set<User> up = comment.getUpVoters();
        Set<User> down = comment.getDownVoters();

        if (up.contains(user)) {
            up.remove(user);
            comment.decreaseRating(user);
        }else if(!down.contains(user)){
            down.add(user);
            comment.decreaseRating(user);
        }

        postCommentRepository.save(comment);
        //return new RedirectView("/postObserver/{postId}");
        model.addAttribute("comment", comment);
        model.addAttribute("user", comment.getUser());
        model.addAttribute("post", comment.getPost());
        model.addAttribute("mainUser", user);
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
        return new ModelAndView("comment.html :: div.fragment");
    }


    @GetMapping("/addComment/{postId}")
    public RedirectView addComment (@PathVariable("postId") Integer postId, @RequestParam String
            comm_body, @RequestParam Integer userId, RedirectAttributes attributes){
        PostComment comment = new PostComment();

        Post p = (Post) postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        User u = (User) userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));

        comment.setId(comment.getId());
        comment.setCommentBody(comm_body);
        comment.setCreationTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        comment.setUser(u);
        comment.setPost(p);
        comment.setLayer(0);

        postCommentRepository.save(comment);
        return new RedirectView("/postObserver/{postId}");
    }

    @CrossOrigin("http://localhost:5000")
    @GetMapping("/editComment/{postId}/{id}/{userId}")
    public ModelAndView editComment (@PathVariable("id") Integer id, @PathVariable("postId") Integer
            postId, @PathVariable("userId") Integer userId, @RequestParam(required = false) String edit_comment_body, @RequestParam(required = false) String
                                             editComment, RedirectAttributes attributes, Model model) throws UnknownHostException {
        PostComment comment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
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
        //return new RedirectView("/postObserver/{postId}");
        model.addAttribute("comment", comment);
        model.addAttribute("user", comment.getUser());
        model.addAttribute("post", comment.getPost());
        model.addAttribute("mainUser", user);
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
        return new ModelAndView("comment.html :: div.fragment");
    }

    @GetMapping("/updateComment/{postId}/{id}/{userId}")
    public RedirectView updateComment (@PathVariable("id") Integer id, @PathVariable("postId") Integer
            postId, @PathVariable("userId") Integer userId, @RequestParam(required = false) String edit_comment_body, @RequestParam(required = false) String
                                             editComment, RedirectAttributes attributes, Model model) throws UnknownHostException {
        PostComment comment = postCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
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
        //return new RedirectView("/postObserver/{postId}");
        model.addAttribute("comment", comment);
        model.addAttribute("user", comment.getUser());
        model.addAttribute("post", comment.getPost());
        model.addAttribute("mainUser", user);
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
        return new RedirectView("/postObserver/{postId}");
    }

    @CrossOrigin("http://localhost:5000")
    @GetMapping(value = "/reply/{userId}/{postId}/{commUserId}/{commentId}")
    public String replyToComment(Model model, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId,
                                 @PathVariable("commUserId") Integer commUserId, @PathVariable("commentId") Integer commentId) throws UnknownHostException {
        model.addAttribute("user", userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId)));
        model.addAttribute("post", postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId)));
        model.addAttribute("commUser", userRepository.findById(commUserId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + commUserId)));
        PostComment root = postCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + commentId));
        model.addAttribute("rootComment", root);
        model.addAttribute("commentCount", root.getSubComments().size());
        model.addAttribute("commentLayer", root.getLayer());
        model.addAttribute("addressUrl", envUtil.getServerUrlPrefi());
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
