package com.example.nodes.controller;

import com.example.nodes.entity.Announcement;
import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Interest;
import com.example.nodes.entity.User;
import com.example.nodes.service.AnnouncementService;
import com.example.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BulletinBoardController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/bulletin-board")
    public String viewBulletinBoard(@AuthenticationPrincipal User user, Model model) {
        List<Competence> competences = user.getCompetences();
        List<Interest> interests = user.getInterests();
        List<Announcement> announcements = null;//announcementService.getAnnouncementsForUser(competences, interests);
        model.addAttribute("announcements", announcements);
        return "bulletin-board";
    }

    @GetMapping("/bulletin")
    public String bulletinPage(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "bulletin-board";
    }

    @PostMapping("/add-announcement")
    public String addAnnouncement(@AuthenticationPrincipal User user, @RequestParam String title, @RequestParam String description) {
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setDescription(description);
        announcement.setAuthor(user.getId());
        announcementService.saveAnnouncement(announcement);
        return "redirect:/bulletin";
    }
}


