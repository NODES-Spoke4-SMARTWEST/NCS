package com.example.nodes.repository;

import com.example.nodes.entity.Announcement;
import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCompetencesInAndInterestsIn(List<Competence> competences, List<Interest> interests);
}
