package it.univda.nodes.controller;

import it.univda.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manage-users";
    }

    @PostMapping("/ban-user")
    public String banUser(@RequestParam("userId") Long userId) {
        userService.banUser(userId);
        return "redirect:/manage-users";
    }

    @PostMapping("/unban-user")
    public String unbanUser(@RequestParam("userId") Long userId) {
        userService.unbanUser(userId);
        return "redirect:/manage-users";
    }
}
