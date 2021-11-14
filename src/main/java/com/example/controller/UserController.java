package com.example.controller;

import com.example.model.CustomUserDetails;
import com.example.model.Role;
import com.example.model.User;
import com.example.repos.GroupRepository;
import com.example.repos.BlogRepository;
import com.example.repos.UserRepository;
import com.example.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor

@Controller
public class UserController {

    @Autowired
    private final UserRepository userRepository;

	@Autowired
    private BlogRepository blogRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String email, @RequestParam String password, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User userFromDB = userRepository.findByEmail(email);
        if (userFromDB != null && (userFromDB.isActive() || userFromDB.isWasBanned())) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        if (userFromDB != null && !userFromDB.isActive()) {
            userFromDB.setPassword(password);
            userFromDB.setPassword_encoded(bCryptPasswordEncoder.encode(password));
            userFromDB.setActive(true);
            userRepository.save(userFromDB);
        } else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setPassword_encoded(bCryptPasswordEncoder.encode(password));
            user.setRole(Collections.singleton(Role.USER));
            user.setActive(true);
            user.setWasBanned(false);
            Blog blog = new Blog();
            user.setBlog(blog);
            blog.setUser(user);
            userRepository.save(user);
            blogRepository.save(blog);
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            model.addAttribute("user", ((CustomUserDetails) principal).getUser());
            return "profile";
        }
        return "redirect:";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            model.addAttribute("user", ((CustomUserDetails) principal).getUser());
            return "editProfile";
        }
        return "redirect:";
    }

    @PostMapping("/editProfile")
    public RedirectView profile(@RequestParam String firstName, @RequestParam String lastName, @RequestParam File pic) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            User u = ((CustomUserDetails) principal).getUser();
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setPic(pic);
            userRepository.save(u);
        }
        return new RedirectView("/editProfile");
    }

    @GetMapping(value = "/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User u = userOptional.get();
            u.setActive(false);
            userRepository.save(u);
        }
        return "registration";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
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
