package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repos.CommentRepository;
import com.example.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration (@RequestParam String email, @RequestParam String password, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User userFromDB = userRepository.findByEmail(email);
        if (userFromDB != null && (userFromDB.isActive() || userFromDB.isWasBanned())){
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        if (userFromDB != null && !userFromDB.isActive()){
            userFromDB.setPassword(password);
            userFromDB.setActive(true);
            userRepository.save(userFromDB);
        } else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(Collections.singleton(Role.USER));
            user.setActive(true);
            user.setWasBanned(false);
            userRepository.save(user);
        }
        //int id = user.getId();
        //User user = userRepository.findById(1).get();
        //user.setFirstName("qwe");
        //userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model
                .addAttribute("user", userRepository.findByEmail(user.getUsername()));
        return "profile";
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
    public RedirectView profile(@RequestParam String firstName, @RequestParam String lastName, @RequestParam File pic){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findByEmail(user.getUsername());
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPic(pic);
        userRepository.save(u);
        return new RedirectView("/profile");
    }

    @GetMapping(value = "/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        //org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User u = userOptional.get();
            u.setActive(false);
            userRepository.save(u);
        }
        return "registration";
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

    @GetMapping(value = "/banUser/{userId}")
    public RedirectView deleteUserForAdmin(@PathVariable("userId") Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User u = userOptional.get();
            u.setActive(false);
            u.setWasBanned(true);
            userRepository.save(u);
        }
        return new RedirectView("/userList");
    }

    @GetMapping(value = "/unbanUser/{userId}")
    public RedirectView resurrectUserForAdmin(@PathVariable("userId") Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User u = userOptional.get();
            u.setActive(true);
            u.setWasBanned(false);
            userRepository.save(u);
        }
        return new RedirectView("/userList");
    }

}
