package com.example.nodes.repository;

import com.example.nodes.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupRepository extends JpaRepository<Group, Long> {
}
