package com.example.nodes.controller;

import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Interest;
import com.example.nodes.entity.User;
import com.example.nodes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.*;

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

    @GetMapping("/offer-facility")
    public String showOfferFacilityForm(Model model) {
        model.addAttribute("hub", new Hub());
        return "offer-facility";
    }



    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB limit
    private static final byte[] DEFAULT_IMAGE = loadDefaultImage();

    /*@PostMapping("/offer-facility")
    public String offerFacility(@ModelAttribute Hub hub, @RequestParam("image") MultipartFile imageFile) {
        int p = 0;
        try {
            if (!imageFile.isEmpty()) {
                hub.setImage(imageFile.getBytes()); // Assuming you have an 'image' field in your entity
            }
            //hubService.saveHub(hub);
            hubService.saveHub(hub, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/offer-facility";
    }*/

    @GetMapping("/image/{hubId}")
    public ResponseEntity<byte[]> getHubImage(@PathVariable Long hubId) {
        byte[] image = hubService.getHubImage(hubId);
        if (image != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/offer-facility")
    public String offerFacility(@ModelAttribute Hub hub, @AuthenticationPrincipal User user) {
        hub.setCreator(user);
        if (hub.getImage() != null) {
            hub.setImage(Base64.getDecoder().decode(hub.getImage()));
        }
        hubService.saveHub(hub);
        return "redirect:/home";
    }


    /*@PostMapping("/offer-facility")
    public String submitHub(@ModelAttribute Hub hub,
                            //@RequestParam(value = "image", required = false) MultipartFile imageFile,
                            @AuthenticationPrincipal User user) {
        hub.setCreator(user);
        int t = 0;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                if (!Objects.requireNonNull(imageFile.getContentType()).equals("image/jpeg")) {
                    hub.setImage(DEFAULT_IMAGE);
                } else if (imageFile.getSize() > MAX_IMAGE_SIZE) {
                    hub.setImage(DEFAULT_IMAGE);
                } else {
                    hub.setImage(imageFile.getBytes());
                }
            } catch (IOException e) {
                hub.setImage(DEFAULT_IMAGE);
            }
        } else {
            hub.setImage(DEFAULT_IMAGE);
        }

        hubService.saveHub(hub);
        return "redirect:/home";
    }*/

    private static byte[] loadDefaultImage() {
        try (InputStream is = HubController.class.getResourceAsStream("/static/images/default.jpg")) {
            return is != null ? is.readAllBytes() : new byte[0];
        } catch (IOException e) {
            return new byte[0];
        }
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