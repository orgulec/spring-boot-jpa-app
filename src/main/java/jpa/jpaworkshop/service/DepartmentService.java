package jpa.jpaworkshop.service;

import jpa.jpaworkshop.exceptions.EmployeeAlreadyInDepartmentException;
import jpa.jpaworkshop.exceptions.NoDepartmentFoundedException;
import jpa.jpaworkshop.model.dto.DepartmentRequest;
import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public Department findDepartmentByName(String name){
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);
        log.info("Finding department by name: " + name);
        return optionalDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public List<Department> findAll(){
        return departmentRepository.findAllBy();
    }
    public Department findById(Long id){
        Optional<Department> optDepartment = departmentRepository.findById(id);
        log.info("Finding department by id: " + id);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public List<Department> findDepartmentsByCity(String city){
        Optional<List<Department>> optDepartment = departmentRepository.findAllByAddress_City(city);
        log.info("Finding department by city: " + city);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public List<Department> findDepartmentsByCountry(String country){
        Optional<List<Department>> optDepartment = departmentRepository.findAllByAddress_Country(country);
        log.info("Finding department by country: " + country);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public Department findDepartmentByEmployees(Employee employee){
        Optional<Department> optDepartment = departmentRepository.findDepartmentByEmployeesContains(employee);
        log.info("Finding department by employee: " + employee);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public Department findByEmployee(String firstName, String lastName){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        Optional<Department> optDepartment = departmentRepository.findByEmployeesIsContaining(employee);
        log.info("Finding department by employee names: " + firstName+" "+lastName);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }
    public Department addEmployeeToDepartment (String depName, String employeeFirstName, String employeeLastName) {
        Employee employee = employeeService.findEmployeeByFirstNameAndLastName(employeeFirstName, employeeLastName);

        Optional<Department> department = departmentRepository.findByName(depName);
        List<Employee> depEmployees = department.get().getEmployees();
        if(depEmployees.contains(employee)){
            throw new EmployeeAlreadyInDepartmentException(depName);
        }
        depEmployees.add(employee);
        department.get().setEmployees(depEmployees);
        employee.setDepartment(department.get());
        log.info("Add employee ("+employee+") to department: " + depName);
        return department.get();
    }
    public Department editDepartmentById(Long id, DepartmentRequest departmentRequest){
        Optional<Department> optDepartment = departmentRepository.findById(id);
        if(optDepartment.isEmpty()){
            throw new NoDepartmentFoundedException();
        }
        Department editDepartment = optDepartment.get();
        if(!departmentRequest.getName().isBlank()) editDepartment.setName(departmentRequest.getName());
        if(departmentRequest.getAddress() != null) editDepartment.setAddress(departmentRequest.getAddress());
        log.info("Editing department by id: " + id);
        return editDepartment;
    }



}
