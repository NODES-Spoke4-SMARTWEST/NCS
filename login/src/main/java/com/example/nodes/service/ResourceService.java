package com.example.nodes.service;

import com.example.nodes.entity.Resource;
import com.example.nodes.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    // Additional resource-related business logic can be added here
}

