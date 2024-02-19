package jpa.jpaworkshop.model.entity;

import jakarta.persistence.*;
import jpa.jpaworkshop.model.EmployeeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    @Enumerated(EnumType.STRING) //dnotacja dla Enuma do wyświetlania nazwy w DB
    private EmployeeType employeeType;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> employees;

}
