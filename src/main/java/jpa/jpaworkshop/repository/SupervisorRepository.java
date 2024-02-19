package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
}
