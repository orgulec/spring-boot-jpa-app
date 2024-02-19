package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.EntryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryCardRepository extends JpaRepository<EntryCard, Long> {
}
