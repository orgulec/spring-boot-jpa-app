package jpa.jpaworkshop.service;

import jpa.jpaworkshop.model.Address;
import jpa.jpaworkshop.model.entity.Department;
import jpa.jpaworkshop.model.entity.Employee;
import jpa.jpaworkshop.model.entity.EntryCard;
import jpa.jpaworkshop.repository.DepartmentRepository;
import jpa.jpaworkshop.repository.EmployeeRepository;
import jpa.jpaworkshop.repository.EntryCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EntryCardRepository entryCardRepository;

    public void createSimpleData(){

        Department department1 = new Department();
        department1.setName("IT");
        department1.setAddress(new Address("Poland", "Warsaw", "Nowy Świat", "00-001"));
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setName("Sales");
        department2.setAddress(new Address("Poland", "Krakow", "Rynek Główny", "31-042"));
        departmentRepository.save(department2);

        Department department3 = new Department();
        department3.setName("Marketing");
        department3.setAddress(new Address("Poland", "Wroclaw", "Rynek", "50-001"));
        departmentRepository.save(department3);

        Department department4 = new Department();
        department4.setName("Finance");
        department4.setAddress(new Address("Poland", "Gdansk", "Targ Węglowy", "80-827"));
        departmentRepository.save(department4);

        Department department5 = new Department();
        department5.setName("HR");
        department5.setAddress(new Address("Poland", "Poznan", "Stary Rynek", "61-772"));
        departmentRepository.save(department5);

        EntryCard entryCard1 = new EntryCard();
        EntryCard entryCard2 = new EntryCard();
        EntryCard entryCard3 = new EntryCard();
        entryCard1.setUuid(UUID.randomUUID());
        entryCard2.setUuid(UUID.randomUUID());
        entryCard3.setUuid(UUID.randomUUID());
        entryCardRepository.save(entryCard1);
        entryCardRepository.save(entryCard2);
        entryCardRepository.save(entryCard3);

        Employee employee1 = new Employee();
        employee1.setFirstName("Janek");
        employee1.setLastName("Kos");
        employee1.setSalary(new BigDecimal("5000"));
        employee1.setContractEnd(LocalDate.of(2025, Month.DECEMBER, 31));
        employee1.setDepartment(department4);
        employee1.setEntryCard(entryCard1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Grzegoż");
        employee2.setLastName("Brzęczyszczykiewicz");
        employee2.setSalary(new BigDecimal("7000"));
        employee2.setContractEnd(LocalDate.of(2027, Month.DECEMBER, 31));
        employee2.setDepartment(department3);
        employee2.setEntryCard(entryCard2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Julian");
        employee3.setLastName("Król");
        employee3.setSalary(new BigDecimal("6500"));
        employee3.setContractEnd(LocalDate.of(2030, Month.DECEMBER, 01));
//        employee3.setDepartment(department3);
        employee3.setEntryCard(entryCard3);

        employeeRepository.save(employee1);  //spring data jpa nie potrzebuje specjalnego obsługiwania sesji!! sam się tym zajmuje interfejs RepositoeyJpa
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

    }

}
