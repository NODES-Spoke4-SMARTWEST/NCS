package com.example.nodes.controller;

import com.example.nodes.dto.HubDTO;
import com.example.nodes.dto.TempHubDto;
import com.example.nodes.entity.Hub;
import com.example.nodes.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TempController {

    @Autowired
    private HubService hubService;

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

    @GetMapping("/api/hubsCriteria")
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
}

