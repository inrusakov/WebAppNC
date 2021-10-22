package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView registration (@RequestParam String email, @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        n.setEmail(email);
        n.setPassword(password);
        n.setRole(Role.USER);
        userRepository.save(n);
        //User user = userRepository.findById(1).get();
        //user.setFirstName("qwe");
        //userRepository.save(user);
        return new RedirectView("/profile");
    }

    @GetMapping("/profile")
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
    }

}
