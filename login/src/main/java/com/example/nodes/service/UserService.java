package com.example.nodes.service;

import com.example.nodes.entity.User;
import com.example.nodes.repository.CompetenceRepository;
import com.example.nodes.repository.InterestRepository;
import com.example.nodes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private InterestRepository interestRepository;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> searchUsers(String username, String competence, String interest) {
        if(username=="") username = null;
        if(competence=="") competence = null;
        if(interest=="") interest = null;
        return userRepository.searchUsers(username, competence, interest);
    }


    public List<String> getAllCompetences() {
        return competenceRepository.findAllCompetences();
    }

    public List<String> getAllInterests() {
        return interestRepository.findAllInterests();
    }
}
