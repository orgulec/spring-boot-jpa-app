package jpa.jpaworkshop.controller;

import jpa.jpaworkshop.model.dto.EmployeeRequest;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmplyeeController {

    public final EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmplyee(@RequestBody EmployeeRequest employeeRequest){

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastName(employeeRequest.getLastName());
        newEmployee.setSalary(employeeRequest.getSalary());
        newEmployee.setContractEnd(employeeRequest.getContractEnd());

        return ResponseEntity.ok(employeeRepository.save(newEmployee));
    }


}
