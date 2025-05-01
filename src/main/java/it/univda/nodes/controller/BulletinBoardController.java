package it.univda.nodes.controller;

import it.univda.nodes.entity.*;
import it.univda.nodes.service.AnnouncementService;
import it.univda.nodes.service.HubService;
import it.univda.nodes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BulletinBoardController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private UserService userService;

    @Autowired
    private HubService hubService;

    @GetMapping("/bulletin-board")
    public String viewBulletinBoard(@AuthenticationPrincipal User user, Model model) {
        List<Competence> competences = user.getCompetences();
        List<Interest> interests = user.getInterests();
        List<Announcement> announcements = null;//announcementService.getAnnouncementsForUser(competences, interests);
        model.addAttribute("announcements", announcements);
        return "bulletin-board";
    }

    @GetMapping("/bulletinn")
    public String bulletinPage(Model model) {
        List<Announcement> announcements = announcementService.getAllActiveAnnouncements();
        model.addAttribute("announcements", announcements);
        return "bulletin-board";
    }

    @GetMapping("/bulletin")
    public String bulletin(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Announcement> announcements = announcementService.getAllActiveAnnouncements();
        model.addAttribute("announcements", announcements);
        model.addAttribute("currentUserId", user.getId());
        model.addAttribute("hubs", hubService.findAllHubs());
        return "bulletin-board";
    }

    @PostMapping("/add-announcement")
    public String addAnnouncement(@RequestParam String title,
                                  @RequestParam String description,
                                  @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                  @RequestParam("hubId") Long hubId,
                                  Principal principal) {

        User user = userService.findByUsername(principal.getName());
        Hub hub = hubService.getHubById(hubId);

        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setDescription(description);
        announcement.setDateTime(dateTime);
        announcement.setHub(hub);
        announcement.setAuthor(user);
        announcement.setDeleted(false);

        announcementService.saveAnnouncement(announcement);
        return "redirect:/bulletin";
    }

    @PostMapping("/announcement/delete/{id}")
    public String deleteAnnouncement(@PathVariable Long id, Principal principal) {
        announcementService.flagAsDeleted(id, principal.getName());
        return "redirect:/bulletin";
    }

}


