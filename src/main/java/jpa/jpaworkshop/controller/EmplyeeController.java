package jpa.jpaworkshop.controller;

import jpa.jpaworkshop.model.dto.EmployeeDto;
import jpa.jpaworkshop.model.dto.EmployeeRequest;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.EmployeeRepository;
import jpa.jpaworkshop.service.EmployeeMapper;
import jpa.jpaworkshop.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmplyeeController {

    public final EmployeeRepository employeeRepository;
    public final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmplyee(@RequestBody EmployeeDto employeeDto){
        Employee newEmployee = employeeMapper.toEntity(employeeDto);
        return ResponseEntity.ok(employeeRepository.save(newEmployee));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAllBy());
    }

    @GetMapping("/findByName")
    public ResponseEntity<EmployeeDto> findByName(@RequestParam String firstName, String lastName){
        EmployeeDto foundedEmployee = employeeService.findEmployeeByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(foundedEmployee);
    }
    @GetMapping("/findByDepartment")
    public ResponseEntity<List<Employee>> findByName(@RequestParam String depName){
        List<Employee> employeeList = employeeService.findEmployeesByDepartmentName(depName);
        return ResponseEntity.ok(employeeList);
    }
    @PostMapping("/editSalaryById")
    public ResponseEntity<Employee> editSalaryById(@RequestParam Long id, BigDecimal salary){
        return ResponseEntity.ok(employeeService.editSalaryById(id, salary));
    }
    @GetMapping("/findByMinMaxSalary")
    public ResponseEntity<List<EmployeeDto>> findEmployeeByMaxSalary(@RequestParam Long min, Long max){
        BigDecimal salaryMin = BigDecimal.valueOf(min);
        BigDecimal salaryMax = BigDecimal.valueOf(max);
        return ResponseEntity.ok(employeeService.findEmployeeBySalaryBetweenMinMax(salaryMin, salaryMax));
    }
    @GetMapping("/findBySalaryLess/{salary}")
    public ResponseEntity<List<EmployeeDto>> findBySalaryLess(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.findAllBySalaryLessThan(salary));
    }
    @GetMapping("/findBySalaryMore/{salary}")
    public ResponseEntity<List<EmployeeDto>> findBySalaryMore(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.findAllBySalaryMoreThan(salary));
    }


}
