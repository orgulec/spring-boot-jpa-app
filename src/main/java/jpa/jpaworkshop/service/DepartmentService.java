package jpa.jpaworkshop.service;

import jakarta.transaction.Transactional;
import jpa.jpaworkshop.exceptions.DepartmentAlreadyExistException;
import jpa.jpaworkshop.exceptions.EmployeeAlreadyInDepartmentException;
import jpa.jpaworkshop.exceptions.NoDepartmentFoundedException;
import jpa.jpaworkshop.model.dto.DepartmentDto;
import jpa.jpaworkshop.model.dto.DepartmentRequest;
import jpa.jpaworkshop.model.dto.EmployeeDto;
import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.DepartmentRepository;
import jpa.jpaworkshop.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    public List<Department> findAll() {
        return departmentRepository.findAllBy();
    }

    public Department findById(Long id) {
        Optional<Department> optDepartment = departmentRepository.findById(id);
        log.info("Finding department by id: " + id);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    public Department findDepartmentByName(String name) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);
        log.info("Finding department by name: " + name);
        return optionalDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    public List<DepartmentDto> findAllByName(String depName) {
        List<Department> allByName = departmentRepository.findAllByName(depName);
        log.info("Finding department by name: " + depName);

        return allByName.stream()
//                .map(entity -> departmentMapper.toDto(entity))
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Department> findDepartmentsByCity(String city) {
        Optional<List<Department>> optDepartment = departmentRepository.findAllByAddress_City(city);
        log.info("Finding department by city: " + city);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    public List<Department> findDepartmentsByCountry(String country) {
        Optional<List<Department>> optDepartment = departmentRepository.findAllByAddress_Country(country);
        log.info("Finding department by country: " + country);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    public Department findDepartmentByEmployees(Employee employee) {
        Optional<Department> optDepartment = departmentRepository.findDepartmentByEmployeesContains(employee);
        log.info("Finding department by employee: " + employee);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    public Department findByEmployee(String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        Optional<Department> optDepartment = departmentRepository.findByEmployeesIsContaining(employee);
        log.info("Finding department by employee names: " + firstName + " " + lastName);
        return optDepartment.orElseThrow(NoDepartmentFoundedException::new);
    }

    @Transactional
    public Department addNewDepartment(DepartmentRequest departmentRequest) {
        Optional<Department> checkDepartment = departmentRepository.findByName(departmentRequest.getName());
        if (checkDepartment.isPresent()) {
            throw new DepartmentAlreadyExistException(departmentRequest.getName());
        }
        DepartmentDto newDepartment = new DepartmentDto();
        newDepartment.setName(departmentRequest.getName());
        newDepartment.setAddress(departmentRequest.getAddress());
        return departmentMapper.toEntity(newDepartment);
    }

    @Transactional
    public Department addEmployeeToDepartment(String depName, String employeeFirstName, String employeeLastName) {
        EmployeeDto employeeDto = employeeService.findEmployeeByFirstNameAndLastName(employeeFirstName, employeeLastName);
        Employee employee = employeeMapper.toEntity(employeeDto);

        Optional<Department> department = departmentRepository.findByName(depName);
        List<Employee> depEmployees = department.get().getEmployees();
        if (depEmployees.contains(employee)) {
            throw new EmployeeAlreadyInDepartmentException(depName);
        }
        depEmployees.add(employee);
        department.get().setEmployees(depEmployees);
        employee.setDepartment(department.get());
        log.info("Add employee (" + employee + ") to department: " + depName);
        return department.get();
    }

    @Transactional
    public Department editDepartmentById(Long id, DepartmentRequest departmentRequest) {
        Optional<Department> optDepartment = departmentRepository.findById(id);
        if (optDepartment.isEmpty()) {
            throw new NoDepartmentFoundedException();
        }
        Department editDepartment = optDepartment.get();
        if (!departmentRequest.getName().isBlank()) editDepartment.setName(departmentRequest.getName());
        if (departmentRequest.getAddress() != null) editDepartment.setAddress(departmentRequest.getAddress());
        log.info("Editing department by id: " + id);
        return editDepartment;
    }

//    @Transactional(rollbackOn = SQLException.class)
    @Transactional
    public List<Department> deleteById(Long id) {
        Optional<Department> optDepartment = departmentRepository.findById(id);
        if (optDepartment.isEmpty()) {
            throw new NoDepartmentFoundedException();
        }
        Department departmentToRemove = optDepartment.get();
        List<Employee> employeeList = departmentToRemove.getEmployees();
        employeeList.forEach(e -> {
            e.setDepartment(null);
            employeeRepository.save(e);
        });
        departmentToRemove.getEmployees().removeAll(employeeList);
        departmentRepository.save(departmentToRemove);

        departmentRepository.deleteDepartmentById(id);
        return new ArrayList<>(departmentRepository.findAllBy());
    }

}
