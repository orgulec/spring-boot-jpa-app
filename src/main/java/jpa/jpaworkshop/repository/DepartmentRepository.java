package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    Optional<Department> findById(Long id);
    List<Department> findAllBy();
    Optional<Department> findByName(String name);
    Optional<List<Department>> findAllByAddress_City(String city);
    Optional<List<Department>> findAllByAddress_Country(String country);
    Optional<Department> findByEmployeesIsContaining(Employee employee);
    Optional<Department> findDepartmentByEmployeesContains(Employee employee);
}
