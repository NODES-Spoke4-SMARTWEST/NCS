package com.example.nodes.controller;

import com.example.nodes.dto.UserDto;
import com.example.nodes.entity.*;
import com.example.nodes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute User user) {
        System.out.println("-----Check");
        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("competences", competenceRepository.findAll());
        model.addAttribute("interests", interestRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        return "signup";
    }

    /*@GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }*/

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam("competences") List<Long> competenceIds,
                               @RequestParam("interests") List<Long> interestIds,
                               @RequestParam("groups") List<Long> groupIds,
                               Model model) {
        if (userRepository.findByName(user.getName()) != null) {
            model.addAttribute("error", "Username already exists.");
            model.addAttribute("competences", competenceRepository.findAll());
            model.addAttribute("interests", interestRepository.findAll());
            model.addAttribute("groups", groupRepository.findAll());
            return "signup";
        }

        try {
            user.setActive(true); // default to active
            user.setBanned(false); // default to not banned

            List<Competence> competences = competenceRepository.findAllById(competenceIds);
            user.setCompetences(competences);

            List<Interest> interests = interestRepository.findAllById(interestIds);
            user.setInterests(interests);

            List<Group> groups = groupRepository.findAllById(groupIds);
            user.setGroups(groups);

            userRepository.save(user);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Error during signup. Please try again.");
            model.addAttribute("competences", competenceRepository.findAll());
            model.addAttribute("interests", interestRepository.findAll());
            model.addAttribute("groups", groupRepository.findAll());
            return "signup";
        }
    }

    /*@PostMapping("/signup")
    public String registerUser(@ModelAttribute UserDto user) {
        User u = new User();
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setActive(true); // default to active
        u.setBanned(false); // default to not banned
        u.setAvailability(false); // default to not available
        userRepository.save(u);
        return "redirect:/login";
    }*/

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}




class a{





}
