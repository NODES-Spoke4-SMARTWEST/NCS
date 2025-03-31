package it.univda.nodes.repository;

import it.univda.nodes.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupRepository extends JpaRepository<Group, Long> {
}
