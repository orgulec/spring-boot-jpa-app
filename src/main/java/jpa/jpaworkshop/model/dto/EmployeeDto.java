package jpa.jpaworkshop.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate contractEnd;

//    private Department department;
}
