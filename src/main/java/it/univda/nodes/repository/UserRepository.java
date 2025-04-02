package it.univda.nodes.repository;

import it.univda.nodes.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByName(String username);

    @Query("SELECT u FROM User u WHERE " +
            "(:username IS NULL OR u.name LIKE %:username%) AND " +
            "(:competence IS NULL OR :competence IN ELEMENTS(u.competences)) AND " +
            "(:interest IS NULL OR :interest IN ELEMENTS(u.interests))")
    List<User> searchUsers(@Param("username") String username,
                           @Param("competence") Competence competence,
                           @Param("interest") Interest interest);

    /*@Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = :role, u.availability = :availability, " +
            "u.competences = :competences, u.interests = :interests, u.location = :location_id " +
            "WHERE u.id = :id")
    int updateUser(@Param("id") Long id,
                   @Param("role") Role role,
                   @Param("availability") boolean availability,
                   @Param("competences") List<Competence> competences,
                   @Param("interests") List<Interest> interests,
                   @Param("location_id") Hub location_id);*/
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = :role, u.availability = :availability, " +
            "u.competences = :competences, u.interests = :interests, u.location = :location " +
            "WHERE u.id = :id")
    int updateUser(@Param("id") Long id,
                   @Param("role") Role role,
                   @Param("availability") boolean availability,
                   @Param("competences") List<Competence> competences,
                   @Param("interests") List<Interest> interests,
                   @Param("location") Hub location);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.competences = :competences, u.interests = :interests WHERE u.id = :id")
    int updateUserCompetencesAndInterests(@Param("id") Long id,
                                          @Param("competences") Competence competence,
                                          @Param("interests") Interest interest);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.competences = :competences WHERE u.id = :id")
    int updateUserCompetence(@Param("id") Long id, @Param("competences") Competence competence);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.interests = :interests WHERE u.id = :id")
    int updateUserInterest(@Param("id") Long id, @Param("interests") Interest interests);


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = :role, u.availability = :availability, u.location = :location_id, u.description = :description " +
            "WHERE u.id = :id")
    int uupdateUser(@Param("id") Long id,
                    @Param("role") Role role,
                    @Param("availability") boolean availability,
                    @Param("location_id") Hub location_id,
                    @Param("description") String description);

    /*@Modifying
    @Query("UPDATE User u SET u.availability = :availability")
    int updateUser(@Param("availability") boolean availability);

     */

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_competence WHERE user_id = :userId", nativeQuery = true)
    void removeUserCompetences(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_interest WHERE user_id = :userId", nativeQuery = true)
    void removeUserInterests(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_competence (user_id, competence_id) VALUES (:userId, :competenceId)", nativeQuery = true)
    void addUserCompetence(@Param("userId") Long userId, @Param("competenceId") Long competenceId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_interest (user_id, interest_id) VALUES (:userId, :interestId)", nativeQuery = true)
    void addUserInterest(@Param("userId") Long userId, @Param("interestId") Long interestId);
}



