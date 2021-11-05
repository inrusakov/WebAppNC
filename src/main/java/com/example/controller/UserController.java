package com.example.controller;

import com.example.model.CustomUserDetails;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.blog.Blog;
import com.example.repos.BlogRepository;
import com.example.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration (@RequestParam String email, @RequestParam String password, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User userFromDB = userRepository.findByEmail(email);
        if (userFromDB != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        user.setPassword_encoded(bCryptPasswordEncoder.encode(password));

        user.setRole(Collections.singleton(Role.USER));
        user.setActive(true);

        Blog blog = new Blog();
        blog.setUser(user);

        user.setBlog(blog);

        userRepository.save(user);
        blogRepository.save(blog);
        int id = user.getId();
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof CustomUserDetails){
            model.addAttribute("user", ((CustomUserDetails) principal).getUser());
            return "profile";
        }
        return "redirect:";
    }

/*
    @GetMapping("/profile/allUsers")
    public List<User> getAllEmployees() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") int userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }
/*
    @GetMapping("/profile/{name}")
    public String profile() {
        return "profile";
    }
    /*
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", (User)userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        return "profile";
    }
    */

    @PostMapping("/profile")
    public RedirectView profile(@RequestParam String firstName, @RequestParam String lastName){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findByEmail(user.getUsername());
        u.setFirstName(firstName);
        u.setLastName(lastName);
        userRepository.save(u);
        return new RedirectView("/profile");
    }

   /* @PostMapping("/profile")
    public RedirectView profile(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName){
        Iterable<User> user = userRepository.findAll();
        for(User u : user){
            if(u.getEmail().equals(email)){
                u.setFirstName(firstName);
                u.setLastName(lastName);
               // u.setPic(pic);
                userRepository.save(u);
            }
        }
        return new RedirectView("/profile");
    }*/

    @GetMapping("/userList")
    public String userList(Model model){
        model.addAttribute("user", userRepository.findAll());
        return "userList";
    }

}
