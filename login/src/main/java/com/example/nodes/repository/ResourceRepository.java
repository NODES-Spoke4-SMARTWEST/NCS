package com.example.nodes.repository;

import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM Resource r JOIN r.bookings b WHERE r.hub_id = :location AND r.type = :type AND b.quantity = :quantity AND b.startDate >= :startDate AND b.endDate <= :endDate")
    List<Resource> findByParameters(@Param("location") String location,
                                   @Param("type") String type,
                                   @Param("quantity") int quantity,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate);

    @Query("SELECT r FROM Resource r WHERE r.hub_id = :location")
    List<Resource> findAllByLocation(@Param("location") Long location);

    @Query("SELECT r FROM Resource r WHERE r.hub_id = :hub")
    List<Resource> findAllByHub(@Param("hub") Hub hub);

    @Query("SELECT r FROM Resource r WHERE r.hub_id = :hub AND r.type = :type")
    List<Resource> findAllByHubAndType(@Param("hub") Hub hub, @Param("type") String type);

    @Query("SELECT DISTINCT r.type FROM Resource r")
    List<String> findDistinctTypes();

    @Query("SELECT DISTINCT r.hub_id FROM Resource r WHERE r.type = :type")
    List<Hub> findHubsByResourceType(String type);

    @Query("SELECT r.id FROM Resource r JOIN r.hub_id h WHERE h.creator.id = :creatorId")
    List<Long> findResourceIdsByCreator(Long creatorId);

    /*
    List<Resource> findAllByLocation(String location);

    List<Resource> findAllByType(String type);

     */
}
