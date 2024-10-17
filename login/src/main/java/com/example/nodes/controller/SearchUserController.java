package com.example.nodes.controller;

import com.example.nodes.entity.User;
import com.example.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search-users")
    public String searchUsersPage(Model model) {
        model.addAttribute("competences", userService.getAllCompetences());
        model.addAttribute("interests", userService.getAllInterests());
        return "search-users";
    }

    @GetMapping("/api/search-users")
    @ResponseBody
    public List<User> searchUsers(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) String competence,
                                  @RequestParam(required = false) String interest) {
        return userService.searchUsers(username, competence, interest);
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-profile";
    }
}
