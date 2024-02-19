package jpa.jpaworkshop.controller;

import jpa.jpaworkshop.model.dto.DepartmentDto;
import jpa.jpaworkshop.model.dto.DepartmentRequest;
import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.repository.DepartmentRepository;
import jpa.jpaworkshop.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> fingAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/findByName/{departmentName}")
    public ResponseEntity<Department> findByName(@PathVariable String departmentName) {
        Department departmentByName = departmentService.findDepartmentByName(departmentName);
        return ResponseEntity.ok(departmentByName);
    }
    @GetMapping("/findAllByName")
    public ResponseEntity<List<DepartmentDto>> findAllByName(@RequestParam String departmentName) {
        List<DepartmentDto> allByName = departmentService.findAllByName(departmentName);
        return ResponseEntity.ok(allByName);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        Department departmentByName = departmentService.findById(id);
        return ResponseEntity.ok(departmentByName);
    }

    @GetMapping("/findByCity")
    public ResponseEntity<List<Department>> findByCity(@RequestParam String city) {
        List<Department> departments = departmentService.findDepartmentsByCity(city);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/findByCountry")
    public ResponseEntity<List<Department>> findByCountry(@RequestParam String country) {
        List<Department> departments = departmentService.findDepartmentsByCountry(country);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/findByEmployee")
    public ResponseEntity<Department> findDepartmentByEmployee(@RequestParam String firstName, String lastName) {
        return ResponseEntity.ok(departmentService.findByEmployee(firstName, lastName));
    }

    @GetMapping("/findByEmployeeBody")
    public ResponseEntity<Department> findDepartmentByEmployees(@RequestBody Employee employee) {
        return ResponseEntity.ok(departmentService.findDepartmentByEmployees(employee));
    }

    @PostMapping("/addNew")
    public ResponseEntity<Department> addNewDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department newDepartment = departmentService.addNewDepartment(departmentRequest);
        return ResponseEntity.ok(departmentRepository.save(newDepartment));
    }

    @PostMapping("/addEmployeeToDepartment")
    public ResponseEntity<Department> addEmployeeToDepartment(@RequestParam String departmentName, String firstName, String lastName) {
        Department updatedDepartment = departmentService.addEmployeeToDepartment(departmentName, firstName, lastName);
        return ResponseEntity.ok(departmentRepository.save(updatedDepartment));
    }

    @PostMapping("/editDepartment/{id}")
    public ResponseEntity<Department> editDepartment(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest) {
        Department editedDepartmentById = departmentService.editDepartmentById(id, departmentRequest);

        return ResponseEntity.ok(departmentRepository.save(editedDepartmentById));
    }


}
