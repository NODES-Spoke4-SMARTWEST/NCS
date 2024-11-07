package com.example.nodes.service;

import com.example.nodes.dto.HubDTO;
import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Interest;
import com.example.nodes.repository.CompetenceRepository;
import com.example.nodes.repository.HubRepository;
import com.example.nodes.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubService {

    @Autowired
    private HubRepository hubRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private InterestRepository interestRepository;

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

    public Hub saveHub(Hub hub) {
        hub.setLatitude(truncateDouble(hub.getLatitude(), 4));
        hub.setLongitude(truncateDouble(hub.getLongitude(), 4));
        return hubRepository.save(hub);
    }

    public List<Hub> findAllHubs() {
        return hubRepository.findAll();
    }

    public List<Hub> getLast4Hubs() {
        return hubRepository.findLast4Hubs();
    }

    public Hub getHubById(Long id) {
        return hubRepository.findById(id).orElse(null);
    }

    public List<Hub> searchHubs(Long location, List<Long> competences, List<Long> interests, List<Long> resources) {
        // TODO Implement search logic here
        return hubRepository.findByCriteria(location, competences, interests, resources);
    }

    public List<Hub> findHubsByCriteria(Interest interests, Competence competences) {
        return hubRepository.findByMyCriteria(interests, competences);
    }

    public List<Hub> findHubsByResources(long resources) {
        return hubRepository.findByResources(resources);
    }

    public List<Hub> getHubsByResourceType(String resourceType) {
        return hubRepository.findByResourceType(resourceType);
    }

    public List<HubDTO> findAllHubDto() {
        List<Hub> hubs = hubRepository.findAll();
        return hubs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public HubDTO convertToDTO(Hub hub) {
        HubDTO dto = new HubDTO();
        dto.setName(hub.getName());
        dto.setDescription(hub.getDescription());
        dto.setLatitude(hub.getLatitude());
        dto.setLongitude(hub.getLongitude());
        dto.setResources(hub.getResources().stream()
                .map(resource -> {
                    HubDTO.ResourceDTO resourceDTO = new HubDTO.ResourceDTO();
                    resourceDTO.setType(resource.getType());
                    return resourceDTO;
                }).collect(Collectors.toList()));
        return dto;
    }

    public List<Hub> findHubsByCompetence(long competence) {
        return hubRepository.findByCompetence(competence);
    }

    public List<Hub> findHubsByInterest(long interest) {
        return hubRepository.findByInterest(interest);
    }
}


/*@Service
public class HubService {

    @Autowired
    private HubRepository hubRepository;

    public List<Hub> findAllHubs() {
        return hubRepository.findAllHubs();
    }


/*
    public List<Hub> searchHubs(String man, String criteria, String resources) {
        // Implement search logic based on location, criteria and resources
        return hubRepository.findHubs(name, criteria, resources);
    }

    public List<String> getAllLocations() {
        return hubRepository.findAllLocations();
    }

    public List<String> getAllResources() {
        return hubRepository.findAllResources();
    }
}

 */
