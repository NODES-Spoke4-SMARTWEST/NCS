package com.example.nodes.repository;

import com.example.nodes.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

    @Query("SELECT h FROM Hub h JOIN h.districts d JOIN d.competences c JOIN d.interests i WHERE "
            + "(COALESCE(:competences, NULL) IS NULL OR c.id IN :competences) AND "
            + "(COALESCE(:interests, NULL) IS NULL OR i.id IN :interests)")
    List<Hub> findByMyCriteria(@Param("interests") Interest interests, @Param("competences") Competence competences);

    @Query("SELECT h FROM Hub h JOIN h.resources r WHERE "
            + "(COALESCE(:resources, NULL) IS NULL OR r.id IN :resources)")
    List<Hub> findByResources(long resources);

    @Query(value = "SELECT * FROM hub ORDER BY id DESC LIMIT 4", nativeQuery = true)
    List<Hub> findLast4Hubs();

    @Query("SELECT DISTINCT h FROM Hub h JOIN h.resources r WHERE r.type = :resourceType")
    List<Hub> findByResourceType(String resourceType);

    @Query("SELECT h FROM Hub h JOIN h.districts d JOIN d.competences c WHERE "
            + "(COALESCE(:competences, NULL) IS NULL OR c.id IN :competences)")
    List<Hub> findByCompetence(@Param("competences") Long competence);

    @Query("SELECT h FROM Hub h JOIN h.districts d JOIN d.interests i WHERE "
            + "(COALESCE(:interests, NULL) IS NULL OR i.id IN :interests)")
    List<Hub> findByInterest(@Param("interests") Long interest);




    @Query("SELECT DISTINCT d FROM District d")
    List<District> findAllDistricts();

    @Query("SELECT DISTINCT h FROM Hub h WHERE h.creator = :user")
    List<Hub> findByCreator(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hub_resource (hub_id, resource_id) VALUES (:hubId, :resourceId)", nativeQuery = true)
    void addHubResource(@Param("hubId") Long hubId, @Param("resourceId") Long resourceId);

    /*@Query("SELECT d FROM District d WHERE (:location IS NULL OR :location MEMBER OF d.hubs.location) AND " +
            "(:competence IS NULL OR :competence MEMBER OF d.competences) AND " +
            "(:interest IS NULL OR :interest MEMBER OF d.interests) AND " +
            "(:resource IS NULL OR :resource MEMBER OF d.hubs.resources.type)")
    List<District> findDistrictsByCriteria(String location, String competence, String interest, String resource);

     */

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