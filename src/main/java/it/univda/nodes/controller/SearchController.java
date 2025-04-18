/*package it.univda.nodes.controller;

import it.univda.nodes.entity.Resource;
import it.univda.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ResourceService resourceService;

    /*
    @Autowired
    private BookingService bookingService;

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        // Load any necessary data for search form
        return "search";
    }

    @GetMapping("/resources")
    public String searchResources(@RequestParam(required = false) String location,
                                  @RequestParam(required = false) String type,
                                  @RequestParam(required = false) Integer quantity,
                                  @RequestParam(required = false) LocalDateTime startDate,
                                  @RequestParam(required = false) LocalDateTime endDate,
                                  Model model) {
        List<Booking> bookings = bookingService.searchBookings(location, type, quantity, startDate, endDate);
        model.addAttribute("bookings", bookings);
        return "resources";
    }
     *//*

    @GetMapping("/search")
    public String showSearchForm() {
        return "search";
    }

    @GetMapping("/resources")
    public String searchResources(@RequestParam String location,
                                  @RequestParam String type,
                                  @RequestParam int quantity,
                                  @RequestParam Date startDate,
                                  @RequestParam Date endDate,
                                  Model model) {
        List<Resource> resources = resourceService.searchResources(location, type, quantity, startDate, endDate);
        model.addAttribute("resources", resources);
        return "resources";
    }
}*/


package it.univda.nodes.controller;

import it.univda.nodes.entity.Hub;
import it.univda.nodes.entity.Resource;
import it.univda.nodes.service.HubService;
import it.univda.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private HubService hubService;
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/search")
    public String showSearchForm(@RequestParam Long hubId, Model model) {
        Hub hub = hubService.getHubById(hubId);
        List<String> resourceTypes = resourceService.getAllDistinctTypes();
        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("hub", hub);
        return "search";
    }

    @GetMapping("/resources")
    public String showResources(@RequestParam Long hubId,
                                @RequestParam String type,
                                @RequestParam int quantity,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                Model model) {
        Hub hub = hubService.getHubById(hubId);
        if (hub != null) {
            List<Resource> resources = resourceService.getResourcesByHubAndType(hub, type);
            model.addAttribute("resources", resources);
            model.addAttribute("quantity", quantity);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        }
        return "resources";
    }

    @GetMapping("/resourcesBis")
    public String showResourcesBis(@RequestParam Long hubId,
                                @RequestParam String type,
                                @RequestParam String quantity,
                                @RequestParam String startDate,
                                @RequestParam String endDate,
                                Model model) {
        int quantityInt = Integer.parseInt(quantity);
        Hub hub = hubService.getHubById(hubId);
        if (hub != null) {
            List<Resource> resources = resourceService.getResourcesByHubAndType(hub, type);
            model.addAttribute("resources", resources);
            model.addAttribute("quantity", quantityInt);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        }
        return "resources";
    }
}




