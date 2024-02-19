package jpa.jpaworkshop.service;

import jpa.jpaworkshop.exceptions.NoEmployeeFoundedException;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee findEmployeeByFirstNameAndLastName(String firstName, String lastName){
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(()->new NoEmployeeFoundedException(firstName+" "+lastName));
    }
    public List<Employee> findEmployeesByDepartmentName(String name){
        Optional<List<Employee>> optDepartment = employeeRepository.findEmployeesByDepartment_Name(name);
        return optDepartment.orElseThrow(()->new NoEmployeeFoundedException("Department: "+ name));
    }

}
