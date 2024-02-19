package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<List<Employee>> findEmployeesByDepartment_Name(String name);

}
