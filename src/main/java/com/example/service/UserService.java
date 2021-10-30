package com.example.service;

import com.example.controller.UserController;
import com.example.model.CustomUserDetails;
import com.example.model.User;
import com.example.repos.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserController userController;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(user);
    }

    public User findUserById(int userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

//    Этот блок преднозначен для перезаписи колонки паролей пользователей на закодированную версию

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByEmail(user.getEmail());
//        if (userFromDB != null) {
//            return false;
//        }
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        System.out.println("email "+"\""+user.toString()+"\"");
//        return true;
//    }

    public boolean deleteUser(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
