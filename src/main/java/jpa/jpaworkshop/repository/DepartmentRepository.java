package jpa.jpaworkshop.repository;

import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    Optional<Department> findById(Long id);
    List<Department> findAllBy();

//    @Query(value = "SELECT * FROM Department WHERE name = :name", nativeQuery = true)   //nativeQuery - do czystych zapytań SQL
    @Query(value = "SELECT d FROM Department d WHERE d.name = :name")
    List<Department> findAllByName(@Param("name") String name);

    Optional<Department> findByName(String name);
    Optional<List<Department>> findAllByAddress_City(String city);
    Optional<List<Department>> findAllByAddress_Country(String country);
    Optional<Department> findByEmployeesIsContaining(Employee employee);
    Optional<Department> findDepartmentByEmployeesContains(Employee employee);
}
