package com.example.nodes.service;

import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Resource;
import com.example.nodes.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HubService {

    @Autowired
    private HubRepository hubRepository;

    public List<Hub> findAllHubs() {
        return hubRepository.findAll();
    }

    public List<Hub> searchHubs(Long location, List<Long> competences, List<Long> interests, List<Long> resources) {
        // TODO Implement search logic here
        return hubRepository.findByCriteria(location, competences, interests, resources);
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
