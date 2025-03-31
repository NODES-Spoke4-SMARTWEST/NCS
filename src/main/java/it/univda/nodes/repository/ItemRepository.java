package it.univda.nodes.repository;

import it.univda.nodes.entity.Item;
import it.univda.nodes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
    List<Item> findByType(String type);
}
