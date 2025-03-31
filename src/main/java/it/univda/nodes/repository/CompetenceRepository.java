package it.univda.nodes.repository;

import it.univda.nodes.entity.Competence;
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
