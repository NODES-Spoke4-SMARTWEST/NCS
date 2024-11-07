package com.example.nodes.service;

import com.example.nodes.entity.*;
import com.example.nodes.repository.CompetenceRepository;
import com.example.nodes.repository.InterestRepository;
import com.example.nodes.repository.RoleRepository;
import com.example.nodes.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

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

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void banUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBanned(true);
        userRepository.save(user);
    }

    public void unbanUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBanned(false);
        userRepository.save(user);
    }

    public void createUser(String username, String password, String role) {
        User user = new User();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        Role userRole = roleRepository.findByName(role);
        user.setRole(userRole);

        userRepository.save(user);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Set<Role> roles = new HashSet<Role>();
        //roles.add(roleRepository.findByName("agent"));
        user.setRole(roleRepository.findByName("agent"));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> searchUsers(String username, String competence, String interest) {
        Competence c = null;
        Interest i = null;
        if(username=="") username = null;
        if(competence!="") c = competenceRepository.findByName(competence);
        if(interest!="") i = interestRepository.findByName(interest);
        return userRepository.searchUsers(username, c, i);
    }


    public List<String> getAllCompetences() {
        return competenceRepository.findAllCompetences();
    }

    public List<String> getAllInterests() {
        return interestRepository.findAllInterests();
    }

    /*public User updateUser(User user){
        User old = getCurrentUser();
        user.setPassword(old.getPassword());
        if(user.getRoles().isEmpty()){
            Set<Role> roles = new HashSet<Role>();
            roles.add(roleRepository.findByName("agent"));
            user.setRoles(roles);
        }
        //userRepository.deleteById(old.getId());
        int res = userRepository.updateUser(user.getId(), user.getRoles(), user.isAvailability(), user.getCompetences(), user.getInterests(), user.getLocation());
        return user;
    }
     */

    @Transactional
    public void uupdateUser(User user) {
        User old = getCurrentUser();

        Long userId = user.getId();
        Role role = user.getRole() != null ? user.getRole() : old.getRole();
        Boolean availability = user.isAvailability();
        List<Competence> competences = user.getCompetences() != null ? user.getCompetences() : old.getCompetences();
        List<Interest> interests = user.getInterests() != null ? user.getInterests() : old.getInterests();
        Hub location = user.getLocation() != null ? user.getLocation() : old.getLocation();

        userRepository.updateUser(userId, role, availability, competences, interests, location);
    }

    @Transactional
    public void updateUser(User user) {
        User old = getCurrentUser();
        String description = user.getDescription();
        Long userId = old.getId();
        Boolean availability = user.isAvailability();
        Role role = user.getRole() != null ? user.getRole() : old.getRole();
        Hub location = user.getLocation() != null ? user.getLocation() : old.getLocation();
        if (user.getDescription() == "Enter your description") old.getDescription();
        userRepository.uupdateUser(userId, role, availability, location, description);
    }

    public int updateUserCompetencesAndInterests(User user) {
        return userRepository.updateUserCompetencesAndInterests(user.getId(), user.getCompetences(), user.getInterests());
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }
}
