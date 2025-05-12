package it.univda.nodes.repository;

import it.univda.nodes.dto.DistrictDTO;
import it.univda.nodes.entity.District;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hub_district (hub_id, district_id) VALUES (:hubId, :districtId)", nativeQuery = true)
    void saveHubs(@Param("districtId")Long districtId, @Param("hubId")Long hubId);

    @Query("SELECT DISTINCT d FROM District d WHERE d.active = true AND d.activeMonth = :month")
    List<District> findByMonth(@Param("month")String month);
}
