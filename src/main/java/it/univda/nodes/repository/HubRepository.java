package it.univda.nodes.repository;

import it.univda.nodes.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HubRepository extends JpaRepository<Hub, Long>, JpaSpecificationExecutor<Hub> {
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

    @Query("SELECT h.image FROM Hub h WHERE h.id = :hubId")
    byte[] findImageByHubId(@Param("hubId") Long hubId);

    @Query("SELECT DISTINCT h FROM Hub h " +
            "LEFT JOIN h.districts d " +
            "LEFT JOIN d.competences c " +
            "LEFT JOIN d.interests i " +
            "LEFT JOIN h.resources r " +
            "WHERE (:location IS NULL OR h.id = :location) " +
            "AND (:competence IS NULL OR c.id = :competence) " +
            "AND (:interest IS NULL OR i.id = :interest) " +
            "AND (:resource IS NULL OR r.id = :resource) " +
            "AND (:municipality IS NULL OR h.municipality.id = :municipality)")
    List<Hub> findFilteredHubs(
            @Param("location") Long location,
            @Param("competence") Long competence,
            @Param("interest") Long interest,
            @Param("resource") Long resource,
            @Param("municipality") Long municipality
    );
}