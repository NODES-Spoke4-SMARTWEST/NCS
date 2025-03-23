package com.example.nodes.controller;

import com.example.nodes.dto.HubDTO;
import com.example.nodes.entity.*;
import com.example.nodes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private HubService hubService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private InitiativeService initiativeService;
    @Autowired
    private UserService userService;

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        List<String> resourceTypes = resourceService.getAllDistinctTypes();
        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("hubs", hubService.findAllHubs());
        return "booking";
    }

    @PostMapping("/booking")
    public String handleBooking(@RequestParam Long hubId, Model model) {
        return "redirect:/search?hubId=" + hubId;
    }

    @PostMapping("/addBooking")
    public String bookResource(
            @RequestParam Long resourceId,
            @RequestParam int quantity,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Model model) {

        Resource resource = resourceService.getResourceById(resourceId);
        if (resource != null) {
            Booking booking = new Booking();
            booking.setUser(userService.getCurrentUser());
            booking.setResource(resource);
            booking.setQuantity(quantity);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            booking.setStartDate(LocalDateTime.parse(startDate, formatter));
            booking.setEndDate(LocalDateTime.parse(endDate, formatter));

            bookingService.saveBooking(booking);
            model.addAttribute("message", "Booking successful");
        } else {
            model.addAttribute("message", "Resource not found");
        }
        return "booking-confirmation";
    }

    @PostMapping("/search-resources")
    public String searchResources(
            @RequestParam("type") String type,
            @RequestParam("quantity") int quantity,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model) {
        model.addAttribute("type", type);
        model.addAttribute("quantity", quantity);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        List<Hub> hubs = resourceService.findHubsByResourceType(type);
        List<HubDTO> hubDto = new ArrayList<>();
        for (Hub h: hubs) {
            hubDto.add(hubService.convertToDTO(h));
        }

        model.addAttribute("hubs", hubDto);
        return "search-resources";
    }

    //calendar

    @GetMapping("/calendar")
    public String getCalendar(@AuthenticationPrincipal User user, Model model) {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> events = new ArrayList<>();
        bookings = bookings;
        if (userService.getCurrentUser().getRole().getName().equals("agent")) {
            bookings = bookingService.getBookingsForAgent(user);
        } else if (userService.getCurrentUser().getRole().getName().equals("business")) {
            bookings = bookingService.getBookingsForBusiness(user);
            events = bookingService.getBookingsForAgent(user);
        } else {
            bookings = bookingService.getAllBookings();
        }
        model.addAttribute("bookings", bookings);
        model.addAttribute("events", events);
        return "calendar";
    }

    /*@GetMapping("/calendar")
    public String getCalendar(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "calendar";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Booking> events = bookingService.getAllBookings();
        model.addAttribute("events", events);
        return "calendar";
    }*/

    @PostMapping("/cancelBooking")
    public String cancelBooking(@RequestParam Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            booking.setCanceled(!booking.isCanceled());
            bookingService.cancelBooking(booking);
        }
        return "redirect:/calendar";
    }

    @GetMapping("/manage-calendar")
    public String manageCalendar(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("events", bookings);
        return "manage-calendar";
    }

    @PostMapping("/approveInitiative")
    public String approveInitiative(@RequestParam Long initiativeId) {
        Initiative initiative = initiativeService.findById(initiativeId);
        if (initiative != null) {
            initiative.setApproved(!initiative.isApproved());
            initiativeService.approveInitiative(initiative);
        }
        return "redirect:/calendar";
    }

    @PostMapping("/deleteBooking")
    public String deleteBooking(@RequestParam Long id) {
        //bookingService.deleteBooking(id);
        return "redirect:/calendar";
    }
}

