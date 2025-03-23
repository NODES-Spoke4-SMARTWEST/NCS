package com.example.nodes.repository;

import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    Competence findById(long id);
    Competence findByName(String c);
    @Query("SELECT c.name FROM Competence c")
    List<String> findAllCompetences();
}
