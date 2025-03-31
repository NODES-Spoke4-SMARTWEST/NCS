package it.univda.nodes.controller;


import it.univda.nodes.entity.Hub;
import it.univda.nodes.entity.User;
import it.univda.nodes.service.HubService;
import it.univda.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HubService hubService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Hub> hubs = hubService.getLast4Hubs();
        model.addAttribute("hubs", hubs);
        return "home";
    }
}
