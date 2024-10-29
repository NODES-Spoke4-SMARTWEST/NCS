package com.example.nodes.controller;

import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Interest;
import com.example.nodes.service.CompetenceService;
import com.example.nodes.service.HubService;
import com.example.nodes.service.InterestService;
import com.example.nodes.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@Controller
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

 */

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
        List<Hub> searchResults = new ArrayList<>();
        model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        model.addAttribute("resources", resourceService.findAllResources());
        model.addAttribute("searchResults", searchResults.isEmpty() ? new ArrayList<>() : searchResults);

        return "searchHub";
    }

    /*@PostMapping("/searchHub/results")
    public List<Hub> searchHubs(@RequestParam Map<String, String> params) {
        String searchType = params.get("searchType");
        List<Hub> h = new ArrayList<Hub>();
        if ("searchByHubs".equals(searchType)) {
            h.add(hubService.getHubById(Long.parseLong(params.get("location"))));
            return h;
        } else if ("searchByInterests".equals(searchType)) {
            return hubService.findHubsByInterestsAndCompetences(params.get("interests"), params.get("competences"));
        } else if ("searchByResources".equals(searchType)) {
            return hubService.findHubsByResources(params.get("resources"));
        }

        return List.of(); // Return an empty list if no search type matches
    }

     */

    /*@PostMapping("/searchHub/results")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchResults(@RequestParam Map<String, String> params) {
        String searchType = params.get("searchType");
        List<Hub> searchResults = new ArrayList<Hub>();
        if ("searchByHubs".equals(searchType)) {
            searchResults.add(hubService.getHubById(Long.parseLong(params.get("location"))));
        } else if ("searchByInterests".equals(searchType)) {
            searchResults = hubService.findHubsByInterestsAndCompetences(params.get("interests"), params.get("competences"));
        } else if ("searchByResources".equals(searchType)) {
            searchResults = hubService.findHubsByResources(params.get("resources"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("searchResults", searchResults);

        return ResponseEntity.ok(response);
    }

     */

    @PostMapping("/searchHub/results")
    public String searchResults(@RequestParam Map<String, String> params, Model model) {
        String searchType = params.get("searchType");
        List<Hub> searchResults = new ArrayList<>();

        if ("searchByHubs".equals(searchType)) {
            // Assuming hubService.getHubById returns a Hub or null
            Hub hub = hubService.getHubById(Long.parseLong(params.get("location")));
            if (hub != null) {
                searchResults.add(hub);
            }
        } else if ("searchByInterests".equals(searchType)) {
            System.out.println(params.get("competences"));
            Competence c = null;
            if(params.get("competences")!=null) c = competenceService.findById(Long.parseLong(params.get("competence")));
            Interest i = null;
            if(params.get("interests")!=null) i = interestService.findById(Long.parseLong(params.get("interest")));
            searchResults = hubService.findHubsByCriteria(i, c);
        } else if ("searchByResources".equals(searchType)) {
            if(params.get("competence")!=null) searchResults = hubService.findHubsByResources(Long.parseLong(params.get("resources")));
        }

        /*model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        model.addAttribute("resources", resourceService.findAllResources());*/
        model.addAttribute("searchResults", searchResults.isEmpty() ? new ArrayList<>() : searchResults);

        //return "fragments/search-results :: searchResults";
        return "searchHub";
    }

}