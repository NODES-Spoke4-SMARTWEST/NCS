package com.example.nodes.controller;

import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Interest;
import com.example.nodes.entity.User;
import com.example.nodes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.*;

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
    @Autowired
    private UserService userService;

    @GetMapping("/searchHubb")
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

    @PostMapping("/searchHub/resultss")
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
            if(params.get("resources")!=null) searchResults = hubService.findHubsByResources(Long.parseLong(params.get("resources")));
        }

        /*model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        model.addAttribute("resources", resourceService.findAllResources());*/
        model.addAttribute("searchResults", searchResults.isEmpty() ? new ArrayList<>() : searchResults);

        //return "fragments/search-results :: searchResults";
        return "searchHub";
    }

    @PostMapping("/searchHubb/results")
    public List<Hub> searchHubs(@RequestParam("location") Long locationId) {
        List<Hub> searchResults = new ArrayList<>();
        searchResults.add(hubService.getHubById(locationId));
        return searchResults;
    }

    @GetMapping("/searchHub")
    public String showSearchHubPage(Model model) {
        List<Hub> hubs = hubService.findAllHubs();
        List<String> resourceTypes = resourceService.getAllDistinctTypes();
        model.addAttribute("hubs", hubs);
        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        return "searchHub";
    }

    @PostMapping("/searchHub/results")
    public String searchHubs(@RequestParam(required = false) Long hubId,
                             @RequestParam(required = false) String resourceType,
                             @RequestParam(required = false) Long competences,
                             @RequestParam(required = false) Long interests,
                             @RequestParam("searchType") String searchType,
                             Model model) {
        List<Hub> searchResults;

        if ("searchByHubs".equals(searchType) && hubId != null) {
            searchResults = List.of(hubService.getHubById(hubId));
        } else if ("searchByResources".equals(searchType) && resourceType != null) {
            searchResults = hubService.getHubsByResourceType(resourceType);
        } else if ("searchByCriteria".equals(searchType) && (competences != null || interests != null)) {
            Competence c = null;
            if(competences!=null) c = competenceService.findById(competences);
            Interest i = null;
            if(interests!=null) i = interestService.findById(interests);
            searchResults = hubService.findHubsByCriteria(i, c);
        } else {
            searchResults = List.of();
        }

        model.addAttribute("searchResults", searchResults);
        return "searchHubResults";
    }

    @GetMapping("/all-hubs")
    public String showAllHubsPage(Model model) {
        List<Hub> hubs = hubService.findAllHubs();
        model.addAttribute("hubs", hubs);
        return "redirect:/districtsCriteria";
        //return "all-hubs";
    }

    /*@PostMapping("/searchHub/results")
    public List<Hub> searchHubs(@RequestParam("location") Long locationId, Model model) {
        List<Hub> searchResults = new ArrayList<>();
        searchResults.add(hubService.getHubById(locationId));
        model.addAttribute("searchResults", searchResults);
        return searchResults;
    }*/

    @GetMapping("/offer-facility")
    public String showOfferFacilityForm(Model model) {
        model.addAttribute("hub", new Hub());
        return "offer-facility";
    }

    @PostMapping("/offer-facility")
    public String submitHub(@ModelAttribute Hub hub, @AuthenticationPrincipal User user) {
        hub.setCreator(user);
        hubService.saveHub(hub);
        return "redirect:/home";
    }

    @GetMapping("/search-hubs")
    public String searchHubPage(Model model) {
        List<Hub> hubs = hubService.findAllHubs();
        List<String> resourceTypes = resourceService.getAllDistinctTypes();
        model.addAttribute("hubs", hubs);
        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        return "search-hubs";
    }

    @GetMapping("/districtsCriteria")
    public String districtPage(Model model) {
        List<Hub> hubs = hubService.findAllHubs();
        List<String> resourceTypes = resourceService.getAllDistinctTypes();
        model.addAttribute("hubs", hubs);
        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("competences", competenceService.findAllCompetences());
        model.addAttribute("interests", interestService.findAllInterests());
        return "districtsCriteria";
    }



    @GetMapping("/my-hubs")
    public String viewMyHubs(Model model, Principal principal) {
        User user = userService.getCurrentUser();
        List<Hub> hubs = hubService.findHubsByCreator(user);
        model.addAttribute("hubs", hubs);
        return "my-hubs";
    }

    @PostMapping("/my-hubs/update-description")
    public String updateHubDescription(@RequestParam Long id, @RequestParam String description) {
        hubService.updateHubDescription(id, description);
        return "redirect:/my-hubs";
    }

    @PostMapping("/my-hubs/add-resource")
    public String addResource(@RequestParam Long hubId, @RequestParam String resourceName, @RequestParam String resourceDescription) {
        hubService.addResourceToHub(hubId, resourceName, resourceDescription);
        return "redirect:/my-hubs";
    }

    @PostMapping("/my-hubs/remove-resource")
    public String removeResource(@RequestParam Long hubId, @RequestParam Long resourceId) {
        hubService.flagResourceAsDeleted(hubId, resourceId);
        return "redirect:/my-hubs";
    }
}