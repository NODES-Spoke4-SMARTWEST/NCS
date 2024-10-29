package com.example.nodes.controller;

import com.example.nodes.entity.User;
import com.example.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Controller
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
        List<User> u = userService.searchUsers(username, competence, interest);
        u = u;
        return u;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-profile";
    }
}

 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchUserController {

    @Autowired
    private UserService userService;

    // Display the search form with competences and interests loaded
    @GetMapping("/search-users")
    public String searchUsersPage(Model model) {
        model.addAttribute("competences", userService.getAllCompetences());
        model.addAttribute("interests", userService.getAllInterests());
        return "search-users";
    }

    // Handle the form submission from /search-users
    @PostMapping("/search-users-results")
    public ModelAndView searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String interest) {

        List<User> users = userService.searchUsers(username, competence, interest);
        ModelAndView modelAndView = new ModelAndView("search-users");
        modelAndView.addObject("competences", userService.getAllCompetences());
        modelAndView.addObject("interests", userService.getAllInterests());
        modelAndView.addObject("users", users);  // Add the search results to the model

        return modelAndView;
    }

    // Display a specific user's profile page
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-profile";
    }
}
