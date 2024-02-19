package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.dto.EmployeeDto;
import jpa.jpaworkshop.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);
    List<Employee> findAllBy();
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<List<Employee>> findEmployeesByDepartment_Name(String name);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary BETWEEN :min AND :max ")
    List<Employee> findEmployeeBySalaryBetweenMinMax(BigDecimal min, BigDecimal max);


}
