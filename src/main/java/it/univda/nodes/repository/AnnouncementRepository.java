package it.univda.nodes.repository;

import it.univda.nodes.entity.Announcement;
import it.univda.nodes.entity.Competence;
import it.univda.nodes.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCompetencesInAndInterestsIn(List<Competence> competences, List<Interest> interests);
}
