package com.example.nodes.controller;

import com.example.nodes.entity.Hub;
import com.example.nodes.service.CompetenceService;
import com.example.nodes.service.HubService;
import com.example.nodes.service.InterestService;
import com.example.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HubController {

    @Autowired
    private HubService hubService;

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private InterestService interestService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/searchHub")
    public String showSearchPage(Model model) {
        model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        model.addAttribute("resources", resourceService.findAllResources());
        return "searchHub";
    }

    @GetMapping("/searchHub/results")
    public String searchHubs(
            @RequestParam(required = false) Long location,
            @RequestParam(required = false) List<Long> competences,
            @RequestParam(required = false) List<Long> interests,
            @RequestParam(required = false) List<Long> resources,
            Model model) {

        List<Hub> searchResults = hubService.searchHubs(location, competences, interests, resources);
        model.addAttribute("searchResults", searchResults);
        return "searchHub";
    }
}


/*@RestController
public class HubController {

    /*@Autowired
    private HubService hubService;

    @GetMapping("/api/search-hubs")
    public List<Hub> searchHubs(@RequestParam(required = false) String location,
                                @RequestParam(required = false) String criteria,
                                @RequestParam(required = false) String resources) {
        return hubService.searchHubs(location, criteria, resources);
    }
}

 */
