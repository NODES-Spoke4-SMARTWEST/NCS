package it.univda.nodes.repository;

import it.univda.nodes.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    Interest findByName(String i);
    @Query("SELECT i.name FROM Interest i")
    List<String> findAllInterests();

    Interest findById(long interest);
}
