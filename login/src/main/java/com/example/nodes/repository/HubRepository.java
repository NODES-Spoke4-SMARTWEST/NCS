package com.example.nodes.repository;

import com.example.nodes.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepository extends JpaRepository<Hub, Long> {
    @Query("SELECT DISTINCT h FROM Hub h")
    List<Hub> findAllHubs();

    @Query("SELECT DISTINCT h.name FROM Hub h")
    List<String> findAllLocations();

    @Query("SELECT h FROM Hub h JOIN h.districts d JOIN d.competences c JOIN d.interests i JOIN h.resources r WHERE "
            + "(COALESCE(:location, NULL) IS NULL OR h.id = :location) AND "
            + "(COALESCE(:competences, NULL) IS NULL OR c.id IN :competences) AND "
            + "(COALESCE(:interests, NULL) IS NULL OR i.id IN :interests) AND "
            + "(COALESCE(:resources, NULL) IS NULL OR r.id IN :resources)")
    List<Hub> findByCriteria(@Param("location") Long location,
                             @Param("competences") List<Long> competences,
                             @Param("interests") List<Long> interests,
                             @Param("resources") List<Long> resources);



    /*@Query("SELECT h FROM Hub h JOIN h.competences c JOIN h.interests i JOIN h.resources r WHERE " +
            "(:location IS NULL OR h.name LIKE %:location%) " +
            "AND (:interest IS NULL OR i.name = :interest) " +
            "AND (:competence IS NULL OR c.name = :competence) " +
            "AND (:resource IS NULL OR r.name = :resource)")
    List<Hub> searchHubs(@Param("location") String name,
                         @Param("interest") String interest,
                         @Param("competence") String competence,
                         @Param("resource") String resource);

    @Query("SELECT DISTINCT h.resources FROM Hub h")
    List<String> findAllResources();
*/
}