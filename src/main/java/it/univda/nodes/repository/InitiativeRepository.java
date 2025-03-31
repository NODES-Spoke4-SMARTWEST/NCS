package it.univda.nodes.repository;

import it.univda.nodes.entity.Initiative;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Initiative i SET i.approved = :approved " +
            "WHERE i.id = :id")
    int approveInitiative(@Param("id") Long id,
                      @Param("approved") boolean approved);

    @Query("SELECT i.id FROM Initiative i WHERE i.creator.id = :creatorId")
    List<Long> findInitiativeIdsByCreator(Long creatorId);
}
