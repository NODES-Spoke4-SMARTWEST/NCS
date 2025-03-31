package it.univda.nodes.controller;

import it.univda.nodes.entity.Notification;
import it.univda.nodes.entity.User;
import it.univda.nodes.service.NotificationService;
import it.univda.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping("/notifications")
    public String viewNoztifications(@AuthenticationPrincipal User user, Model model) {
        Long userId = user.getId(); // Get the logged-in user's ID
        List<Notification> notifications = notificationService.getNotificationsForUser(userId);
        model.addAttribute("notifications", notifications);
        return "notifications"; // Return the notifications view
    }
}
