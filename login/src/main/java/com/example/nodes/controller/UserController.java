package com.example.nodes.controller;

import com.example.nodes.entity.User;
import com.example.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(@RequestParam("id") int id, Model model) {

        System.out.println("---- check: " + id);

        User user =null/* userService.getUserById(id)*/;
        model.addAttribute("user", user);
        return "user"; // This should correspond to the user.html template
    }
}


