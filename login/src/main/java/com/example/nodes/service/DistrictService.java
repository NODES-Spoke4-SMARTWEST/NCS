package com.example.nodes.service;

import com.example.nodes.entity.District;
import com.example.nodes.entity.Hub;
import com.example.nodes.repository.DistrictRepository;
import com.example.nodes.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private HubRepository hubRepository;

    public District saveDistrict(District district) {
        return districtRepository.save(district);
    }

    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    public void toggleDistrictActive(Long id) {
        District district = districtRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid district ID: " + id));
        district.setActive(!district.isActive());
        districtRepository.save(district);
    }

    public List<Hub> findAllHubs() {
        return hubRepository.findAll();
    }
}


