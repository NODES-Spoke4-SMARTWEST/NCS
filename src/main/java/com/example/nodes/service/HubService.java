package com.example.nodes.service;

import com.example.nodes.dto.DistrictDTO;
import com.example.nodes.dto.HubDTO;
import com.example.nodes.entity.*;
import com.example.nodes.repository.CompetenceRepository;
import com.example.nodes.repository.HubRepository;
import com.example.nodes.repository.InterestRepository;
import com.example.nodes.repository.ResourceRepository;
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

    @Autowired
    private ResourceRepository resourceRepository;

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
        dto.setId(hub.getId());
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






    /*public List<HubDTO> findHubs(String location, String competence, String interest, String resource) {
        List<Hub> hubs = hubRepository.findHubsByCriteria(location, competence, interest, resource);
        return hubs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

     */

    public List<DistrictDTO> findDistricts() {
        // Mocked data: Implement your actual logic to fetch districts based on criteria
        // This is a placeholder example
        List<DistrictDTO> districts = hubRepository.findAllDistricts().stream()
                .map(district -> {
                    DistrictDTO dto = new DistrictDTO();
                    dto.setName(district.getName());
                    dto.setColor("#FF0000"); // Example color
                    if(district.getColor()!=null) dto.setColor(district.getColor());
                    //dto.setMinimumRadius(computeMinimumRadius(district));
                    dto.setMinimumRadius(1000000.0);
                    dto.setHubs(district.getHubs().stream().map(this::convertToDTO).collect(Collectors.toList()));
                    return dto;
                }).collect(Collectors.toList());

        return districts;
    }

    private double computeMinimumRadius(District district) {
        // Example calculation of minimum radius based on hubs' coordinates
        // Here, we simply take the maximum distance between any two hubs in the district
        double maxDistance = 0.0;
        List<Hub> hubs = district.getHubs();
        for (int i = 0; i < hubs.size(); i++) {
            for (int j = i + 1; j < hubs.size(); j++) {
                double distance = computeDistance(hubs.get(i), hubs.get(j));
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }
        return maxDistance / 2; // Radius is half the maximum distance
    }

    private double computeDistance(Hub hub1, Hub hub2) {
        double lat1 = hub1.getLatitude();
        double lon1 = hub1.getLongitude();
        double lat2 = hub2.getLatitude();
        double lon2 = hub2.getLongitude();

        // Using Haversine formula to calculate the distance between two points on the Earth
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Convert to kilometers

        return distance;
    }

    public List<Hub> findHubsByCreator(User user) {
        return hubRepository.findByCreator(user);
    }

    public void updateHubDescription(Long id, String description) {
        Hub hub = hubRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid hub Id:" + id));
        hub.setDescription(description);
        hubRepository.save(hub);
    }

    public void addResourceToHub(Long hubId, String resourceName, String resourceDescription) {
        Hub hub = hubRepository.findById(hubId).orElseThrow(() -> new IllegalArgumentException("Invalid hub Id:" + hubId));
        Resource resource = new Resource();
        resource.setType(resourceName);
        resource.setDescription(resourceDescription);
        resource.setLocation(hub);
        resourceRepository.save(resource);
        //hubRepository.addHubResource(hubId, resource.getId());
    }

    public void flagResourceAsDeleted(Long hubId, Long resourceId) {
        Hub hub = hubRepository.findById(hubId).orElseThrow(() -> new IllegalArgumentException("Invalid hub Id:" + hubId));
        Resource resource = hub.getResources().stream().filter(r -> r.getId().equals(resourceId)).findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid resource Id:" + resourceId));
        resource.setDeleted(true);
        resourceRepository.save(resource);
    }

    /*public HubDTO convertToDTO(Hub hub) {
        HubDTO dto = new HubDTO();
        dto.setId(hub.getId());
        dto.setName(hub.getName());
        dto.setDescription(hub.getDescription());
        dto.setLatitude(hub.getLatitude());
        dto.setLongitude(hub.getLongitude());
        dto.setResources(hub.getResources().stream().map(resource -> {
            HubDTO.ResourceDTO resourceDTO = new HubDTO.ResourceDTO();
            resourceDTO.setId(resource.getId());
            resourceDTO.setType(resource.getType());
            return resourceDTO;
        }).collect(Collectors.toList()));
        return dto;
    }

     */
}
