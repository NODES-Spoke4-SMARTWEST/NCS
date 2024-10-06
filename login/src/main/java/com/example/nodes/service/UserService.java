package com.example.nodes.service;

import com.example.nodes.entity.User;
import com.example.nodes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}


/*
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {

        System.out.println("---- check: " + id);

        return userRepository.findById(id)
                .filter(user -> user.isActive() && !user.isBanned()) // Check if the user is active and not banned
                .orElse(null);
    }

    public String getUserNameById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getName).orElse(null);
    }
}
*/