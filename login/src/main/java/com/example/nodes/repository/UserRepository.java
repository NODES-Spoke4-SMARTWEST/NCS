package com.example.nodes.repository;

import com.example.nodes.entity.*;
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
            "(:competence IS NULL OR :competence MEMBER OF u.competences) AND " +
            "(:interest IS NULL OR :interest MEMBER OF u.interests)")
    List<User> searchUsers(String username, Competence competence, Interest interest);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = :role, u.availability = :availability, " +
            "u.competences = :competences, u.interests = :interests, u.location = :location_id " +
            "WHERE u.id = :id")
    int updateUser(@Param("id") Long id,
                   @Param("role") Role role,
                   @Param("availability") boolean availability,
                   @Param("competences") List<Competence> competences,
                   @Param("interests") List<Interest> interests,
                   @Param("location_id") Hub location_id);

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
}



