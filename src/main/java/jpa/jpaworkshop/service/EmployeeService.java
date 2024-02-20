package jpa.jpaworkshop.service;

import jakarta.transaction.Transactional;
import jpa.jpaworkshop.exceptions.NoEmployeeFoundedException;
import jpa.jpaworkshop.model.dto.EmployeeDto;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto findEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        Employee employee = employeeRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(
                () -> new NoEmployeeFoundedException(firstName + " " + lastName));
        log.info("Find employee by name: "+firstName + " " + lastName);
        return employeeMapper.toDto(employee);
    }

    public List<Employee> findEmployeesByDepartmentName(String name) {
        Optional<List<Employee>> optDepartment = employeeRepository.findEmployeesByDepartment_Name(name);
        log.info("Find employees by department: "+name);
        return optDepartment.orElseThrow(() -> new NoEmployeeFoundedException("Department: " + name));
    }

    @Transactional
    public Employee editSalaryById(Long id, BigDecimal salary) {
        Optional<Employee> emplById = employeeRepository.findById(id);
        if (emplById.isEmpty()) {
            throw new NoEmployeeFoundedException("Id: " + id);
        }
        Employee employee = emplById.get();
        employee.setSalary(salary);
        log.info("Edit salary by id: "+id);
        return employeeRepository.save(employee);
    }

    public List<EmployeeDto> findEmployeeBySalaryBetweenMinMax(BigDecimal min, BigDecimal max) {
        List<Employee> employeeBySalaryBetweenMinMax = employeeRepository.findEmployeeBySalaryBetweenMinMax(min, max);
        if (employeeBySalaryBetweenMinMax.isEmpty()) {
            throw new NoEmployeeFoundedException("with that salary");
        }
        return employeeBySalaryBetweenMinMax
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Employee> findAllBy() {
        return employeeRepository.findAllBy();
    }

    public List<EmployeeDto> findAllBySalaryLessThan(BigDecimal salary) {
        List<Employee> employeesBySalary = employeeRepository.findEmployeesBySalaryLessThanOrderBySalaryDesc(salary);
        if (employeesBySalary.isEmpty()) {
            throw new NoEmployeeFoundedException("with that salary");
        }
        return employeesBySalary
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public List<EmployeeDto> findAllBySalaryMoreThan(BigDecimal salary) {
        List<Employee> employeesBySalary = employeeRepository.findEmployeesBySalaryIsGreaterThanOrderBySalaryDesc(salary);
        if (employeesBySalary.isEmpty()) {
            throw new NoEmployeeFoundedException("with that salary");
        }
        return employeesBySalary
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

}
