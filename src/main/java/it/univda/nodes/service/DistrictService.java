package it.univda.nodes.service;

import it.univda.nodes.dto.DistrictDTO;
import it.univda.nodes.dto.HubDTO;
import it.univda.nodes.entity.District;
import it.univda.nodes.entity.Hub;
import it.univda.nodes.repository.DistrictRepository;
import it.univda.nodes.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private HubRepository hubRepository;

    public void saveDistrict(District district) {
        districtRepository.save(district);
        List<Hub> hubs = district.getHubs();
        for (Hub hub : hubs) {
            districtRepository.saveHubs(district.getId(), hub.getId());
        }

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

    public List<DistrictDTO> findDistrictsByMonth(String month) {
        List<District> districts = districtRepository.findByMonth(month);

        return districts.stream()
                .filter(District::isActive) // optional: if not already filtered in query
                .map(district -> {
                    DistrictDTO dto = new DistrictDTO();
                    dto.setName(district.getName());
                    dto.setColor(district.getColor());
                    dto.setMinimumRadius(1);

                    // Convert Hubs to HubDTOs
                    List<HubDTO> hubDTOs = district.getHubs().stream()
                            .map(hub -> {
                                HubDTO hubDTO = new HubDTO();
                                hubDTO.setId(hub.getId());
                                hubDTO.setName(hub.getName());
                                hubDTO.setLatitude(hub.getLatitude());
                                hubDTO.setLongitude(hub.getLongitude());
                                // add other fields as needed
                                return hubDTO;
                            })
                            .collect(Collectors.toList());
                    dto.setHubs(hubDTOs);

                    dto.setCompetences(district.getCompetences());
                    dto.setInterests(district.getInterests());

                    return dto;
                })
                .collect(Collectors.toList());
    }

}


