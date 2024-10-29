/*package com.example.nodes.controller;

import com.example.nodes.entity.Initiative;
import com.example.nodes.entity.Hub;
import com.example.nodes.service.InitiativeService;
import com.example.nodes.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InitiativeController {

    @Autowired
    private InitiativeService initiativeService;

    @Autowired
    private HubRepository hubRepository;

    @GetMapping("/manage-initiative")
    public String manageInitiative(Model model) {
        model.addAttribute("initiative", new Initiative());
        model.addAttribute("hubs", hubRepository.findAll());
        return "manage-initiative";
    }

    @PostMapping("/manage-initiative")
    public String addInitiative(@ModelAttribute Initiative initiative) {
        initiativeService.saveInitiative(initiative);
        return "redirect:/home";
    }
}*/

package com.example.nodes.controller;

        import com.example.nodes.entity.Booking;
        import com.example.nodes.entity.Hub;
        import com.example.nodes.entity.Initiative;
        import com.example.nodes.service.BookingService;
        import com.example.nodes.service.InitiativeService;
        import com.example.nodes.repository.HubRepository;
        import com.example.nodes.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import java.time.LocalDateTime;
        import java.util.List;

@Controller
public class InitiativeController {

    @Autowired
    private InitiativeService initiativeService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private HubRepository hubRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/manage-initiative")
    public String manageInitiative(Model model) {
        model.addAttribute("hubs", hubRepository.findAll());
        return "manage-initiative";
    }

    @PostMapping("/manage-initiative")
    public String registerInitiative(@RequestParam("subject") String subject,
                                     @RequestParam("locationId") Long locationId,
                                     @RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate) {

        Hub location = hubRepository.findById(locationId).orElse(null);
        /*if (location == null) {
            return "redirect:/manage-initiative?error=LocationNotFound";
        }

         */

        // Create and save Initiative
        Initiative initiative = new Initiative();
        initiative.setSubject(subject);
        initiative.setLocation(location);
        initiative.setApproved(false);  // Set to false by default
        Initiative savedInitiative = initiativeService.saveInitiative(initiative);

        // Create and save Booking with start and end dates
        Booking booking = new Booking();
        booking.setUser(userService.getCurrentUser());
        booking.setInitiative(savedInitiative);
        booking.setStartDate(LocalDateTime.parse(startDate));
        booking.setEndDate(LocalDateTime.parse(endDate));
        bookingService.saveBooking(booking);

        return "redirect:/home";
    }
}
