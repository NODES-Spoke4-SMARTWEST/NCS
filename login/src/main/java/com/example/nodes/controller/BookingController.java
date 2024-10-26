/*package com.example.nodes.controller;

import com.example.nodes.entity.Booking;
import com.example.nodes.entity.Resource;
import com.example.nodes.service.BookingService;
import com.example.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/booking")
    public String showBookingForm() {
        return "booking";
    }

    @PostMapping("/booking")
    public String handleBooking(Booking booking, Model model) {
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Booking successful");
        return "redirect:/search";
    }

    /*@GetMapping("/booking")
    public String showBookingForm(Model model) {
        model.addAttribute("locations", resourceService.getAllResources().stream().map(Resource::getLocation).distinct().toList());
        model.addAttribute("types", resourceService.getAllResources().stream().map(Resource::getType).distinct().toList());
        return "booking";
    }*//*
}*/


package com.example.nodes.controller;

import com.example.nodes.entity.Booking;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Resource;
import com.example.nodes.service.BookingService;
import com.example.nodes.service.HubService;
import com.example.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private HubService hubService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ResourceService resourceService;

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
        model.addAttribute("hubs", hubs);
        return "search-resources";
    }

    //calendar

    @GetMapping("/calendar")
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
    }

    /*@PostMapping("/addBooking")
    public String addBooking(@RequestParam String title,
                             @RequestParam LocalDateTime startDate,
                             @RequestParam LocalDateTime endDate,
                             @RequestParam int quantity) {
        Booking booking = new Booking();
        booking.setTitle(title); // Assuming Booking has a title field; otherwise, adjust as necessary
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setQuantity(quantity);
        bookingService.saveBooking(booking);
        return "redirect:/calendar";
    }

    @PostMapping("/deleteBooking")
    public String deleteBooking(@RequestParam Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/calendar";
    }

     */

}

