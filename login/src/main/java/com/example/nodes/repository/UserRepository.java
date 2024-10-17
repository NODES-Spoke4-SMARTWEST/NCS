package com.example.nodes.repository;

import com.example.nodes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    @Query("SELECT u FROM User u WHERE " +
            "(:username IS NULL OR u.name LIKE %:username%) AND " +
            "(:competence IS NULL OR :competence MEMBER OF u.competences) AND " +
            "(:interest IS NULL OR :interest MEMBER OF u.interests)")
    List<User> searchUsers(String username, String competence, String interest);
}



