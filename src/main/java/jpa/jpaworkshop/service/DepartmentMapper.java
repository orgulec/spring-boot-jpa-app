package jpa.jpaworkshop.service;

import jpa.jpaworkshop.model.dto.DepartmentDto;
import jpa.jpaworkshop.model.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDto toDto(Department entity){
        DepartmentDto dto = new DepartmentDto();
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public Department toEntity(DepartmentDto dto){
        Department entity = new Department();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        return entity;
    }

}
