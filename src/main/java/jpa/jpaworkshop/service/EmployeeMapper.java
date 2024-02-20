package jpa.jpaworkshop.service;

import jpa.jpaworkshop.model.dto.EmployeeDto;
import jpa.jpaworkshop.model.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDto toDto(Employee entity){
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSalary(entity.getSalary());
        dto.setContractEnd(entity.getContractEnd());
        return dto;
    }

    public Employee toEntity(EmployeeDto dto){
        Employee entity = new Employee();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setSalary(dto.getSalary());
        entity.setContractEnd(dto.getContractEnd());
        return entity;
    }
}
