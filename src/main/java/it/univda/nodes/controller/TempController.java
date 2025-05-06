package it.univda.nodes.controller;

import it.univda.nodes.dto.DistrictDTO;
import it.univda.nodes.dto.HubDTO;
import it.univda.nodes.dto.PointDTO;
import it.univda.nodes.dto.TempHubDto;
import it.univda.nodes.entity.Hub;
import it.univda.nodes.entity.PointOfInterest;
import it.univda.nodes.service.DistrictService;
import it.univda.nodes.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TempController {

    @Autowired
    private HubService hubService;
    @Autowired
    private DistrictService districtService;

    double truncateDouble(double number, int numDigits) {
        double result = number;
        String arg = "" + number;
        int idx = arg.indexOf('.');
        if (idx!=-1) {
            if (arg.length() > idx+numDigits) {
                arg = arg.substring(0,idx+numDigits+1);
                result  = Double.parseDouble(arg);
            }
        }
        return result ;
    }

    @GetMapping("/api/hubs")
    public List<TempHubDto> getAllHubs() {
        List<Hub> hubs = hubService.findAllHubs();
        List<TempHubDto> res = new ArrayList<>();
        for (Hub hub: hubs) {
            res.add(new TempHubDto(hub.getName(), truncateDouble(hub.getLatitude(),4), truncateDouble(hub.getLongitude(),4)));
        }
        return res;
    }

    @GetMapping("/api/jsonHubs")
    public List<HubDTO> getAllHubDto() {
        return hubService.findAllHubDto();
    }

    /*@GetMapping("/api/hubsCriteria_old")
    public List<HubDTO> getFilteredHubs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String interest,
            @RequestParam(required = false) String resource) {

        List<HubDTO> searchResultsLocation = new ArrayList<>();
        List<HubDTO> searchResultsCompetence = new ArrayList<>();
        List<HubDTO> searchResultsInterest = new ArrayList<>();
        List<HubDTO> searchResultsResource = new ArrayList<>();

        if (location != null && !location.isEmpty()) {
            Hub hub = hubService.getHubById(Long.parseLong(location));
            if (hub != null) {
                searchResultsLocation.add(hubService.convertToDTO(hub));
            }
        }

        if (competence != null && !competence.isEmpty()) {
            List<Hub> hubC = hubService.findHubsByCompetence(Long.parseLong(competence));
            for (Hub h : hubC) {
                searchResultsCompetence.add(hubService.convertToDTO(h));
            }
        }

        if (interest != null && !interest.isEmpty()) {
            List<Hub> hubI = hubService.findHubsByInterest(Long.parseLong(interest));
            for (Hub h : hubI) {
                searchResultsInterest.add(hubService.convertToDTO(h));
            }
        }

        if (resource != null && !resource.isEmpty()) {
            List<Hub> hubR = hubService.getHubsByResourceType(resource);
            for (Hub h : hubR) {
                searchResultsResource.add(hubService.convertToDTO(h));
            }
        }

        // Intersect non-empty lists
        List<List<HubDTO>> nonEmptyLists = new ArrayList<>();
        if (!searchResultsLocation.isEmpty()) nonEmptyLists.add(searchResultsLocation);
        if (!searchResultsCompetence.isEmpty()) nonEmptyLists.add(searchResultsCompetence);
        if (!searchResultsInterest.isEmpty()) nonEmptyLists.add(searchResultsInterest);
        if (!searchResultsResource.isEmpty()) nonEmptyLists.add(searchResultsResource);

        List<HubDTO> searchResults = intersectHubs(nonEmptyLists);

        return searchResults.isEmpty() ? hubService.findAllHubDto() : searchResults;
    }

     */

    @GetMapping("/hubsCriteria")
    public List<HubDTO> getFilteredHubs(
            Model model,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String interest,
            @RequestParam(required = false) String resource,
            @RequestParam(required = false) String municipality) {
        model.addAttribute("districts", districtService.findAllDistricts());
        return hubService.getFilteredHubs(location, competence, interest, resource, municipality);
    }


    private List<HubDTO> intersectHubs(List<List<HubDTO>> lists) {
        if (lists.isEmpty()) return new ArrayList<>();

        Set<Long> commonIds = new HashSet<>();
        for (HubDTO hub : lists.get(0)) {
            commonIds.add(hub.getId());
        }

        for (int i = 1; i < lists.size(); i++) {
            Set<Long> currentIds = new HashSet<>();
            for (HubDTO hub : lists.get(i)) {
                currentIds.add(hub.getId());
            }
            commonIds.retainAll(currentIds);
        }

        // Rebuild list of HubDTOs based on common IDs
        List<HubDTO> result = new ArrayList<>();
        for (HubDTO hub : lists.get(0)) {
            if (commonIds.contains(hub.getId())) {
                result.add(hub);
            }
        }

        return result;
    }


    /*@GetMapping("/api/hubsCriteria")
    public List<HubDTO> getFilteredHubs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String interest,
            @RequestParam(required = false) String resource) {

        List<HubDTO> searchResults = new ArrayList<>();

        if (location != "") {
            Hub hub = hubService.getHubById(Long.parseLong(location));
            if (hub != null) {
                searchResults.add(hubService.convertToDTO(hub));
            }
        }

        if (competence != "") {
            List<Hub> hubC = hubService.findHubsByCompetence(Long.parseLong(competence));
            if (!hubC.isEmpty()) {
                for (Hub h: hubC) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (interest != "") {
            List<Hub> hubI = hubService.findHubsByInterest(Long.parseLong(interest));
            if (!hubI.isEmpty()) {
                for (Hub h: hubI) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (resource != "") {
            List<Hub> hubR = hubService.getHubsByResourceType(resource);
            if (!hubR.isEmpty()) {
                for (Hub h: hubR) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (searchResults.isEmpty()) return hubService.findAllHubDto();
        else return searchResults;
    }

     */

    @GetMapping("/api/hubsRes")
    public List<HubDTO> getResHubs(@RequestParam(required = false) Hub location) {
        List<HubDTO> searchResults = new ArrayList<>();
        Hub hub = hubService.getHubById(location.getId());
        if (hub != null) {
            searchResults.add(hubService.convertToDTO(hub));
        }
        if (searchResults.isEmpty()) return hubService.findAllHubDto();
        else return searchResults;
    }






    @GetMapping("/api/districtsCriteria")
    public Map<String, Object> getFilteredHubsAndDistricts(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String competence,
            @RequestParam(required = false) String interest,
            @RequestParam(required = false) String resource,
            @RequestParam(required = false) String municipality) {

        /*List<HubDTO> searchResults = new ArrayList<>();

        if (location != "") {
            Hub hub = hubService.getHubById(Long.parseLong(location));
            if (hub != null) {
                searchResults.add(hubService.convertToDTO(hub));
            }
        }

        if (competence != "") {
            List<Hub> hubC = hubService.findHubsByCompetence(Long.parseLong(competence));
            if (!hubC.isEmpty()) {
                for (Hub h: hubC) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (interest != "") {
            List<Hub> hubI = hubService.findHubsByInterest(Long.parseLong(interest));
            if (!hubI.isEmpty()) {
                for (Hub h: hubI) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (resource != "") {
            List<Hub> hubR = hubService.getHubsByResourceType(resource);
            if (!hubR.isEmpty()) {
                for (Hub h: hubR) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

        if (municipality != "") {
            List<Hub> hubM = hubService.getFilteredHubs(location, competence, interest, resource, municipality);
            if (!hubM.isEmpty()) {
                for (Hub h: hubM) {
                    searchResults.add(hubService.convertToDTO(h));
                }
            }
        }

         */
        List<HubDTO> searchResults = hubService.getFilteredHubs(location, competence, interest, resource, municipality);
        List<DistrictDTO> districts = hubService.findDistricts();
        List<PointOfInterest> points = hubService.findPOI();
        Map<String, Object> result = new HashMap<>();
        result.put("hubs", getUniqueHubsById(searchResults));
        result.put("districts", districts);
        result.put("points", points);
        return result;
    }

    public List<HubDTO> getUniqueHubsById(List<HubDTO> hubs) {
        // Create a map with hub ID as the key and HubDTO as the value
        Map<Long, HubDTO> hubMap = hubs.stream()
                .collect(Collectors.toMap(HubDTO::getId, hub -> hub, (existing, replacement) -> existing));

        // Return the values of the map as a list
        return hubMap.values().stream().collect(Collectors.toList());
    }
}

