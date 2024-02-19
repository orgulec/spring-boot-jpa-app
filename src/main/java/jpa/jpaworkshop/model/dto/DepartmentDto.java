package jpa.jpaworkshop.model.dto;

import jpa.jpaworkshop.model.Address;
import lombok.Data;

@Data
public class DepartmentDto {
    private String name;
    private Address address;
}
