package com.example.nodes.repository;

import com.example.nodes.entity.District;
import com.example.nodes.entity.Hub;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hub_district (hub_id, district_id) VALUES (:hubId, :districtId)", nativeQuery = true)
    void saveHubs(@Param("districtId")Long districtId, @Param("hubId")Long hubId);
}
