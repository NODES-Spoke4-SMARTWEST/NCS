/*package com.example.nodes.service;

import com.example.nodes.entity.Resource;
import com.example.nodes.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    /*public List<Resource> getResourcesByLocation(String location) {
        return resourceRepository.findAllByLocation(location);
    }

    public List<Resource> getResourcesByType(String type) {
        return resourceRepository.findAllByType(type);
    }

     *//*

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    public List<Resource> searchResources(String location, String type, int quantity, Date startDate, Date endDate) {
        return resourceRepository.findByParameters(location, type, quantity, startDate, endDate);
    }
}*/

package com.example.nodes.service;

import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Resource;
import com.example.nodes.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getResourcesByLocation(Long location) {
        return resourceRepository.findAllByLocation(location);
    }

    public List<Resource> getResourcesByHub(Hub hub) {
        return resourceRepository.findAllByHub(hub);
    }

    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    public List<Resource> getResourcesByHubAndType(Hub hub, String type) {
        return resourceRepository.findAllByHubAndType(hub, type);
    }

    public List<String> getAllDistinctTypes() {
        return resourceRepository.findDistinctTypes();
    }

    public List<Hub> findHubsByResourceType(String type) {
        return resourceRepository.findHubsByResourceType(type);
    }
}


