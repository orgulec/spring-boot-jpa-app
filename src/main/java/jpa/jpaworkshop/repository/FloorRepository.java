package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {
}
