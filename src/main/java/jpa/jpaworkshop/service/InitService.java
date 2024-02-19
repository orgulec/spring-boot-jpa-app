package jpa.jpaworkshop.service;

import jpa.jpaworkshop.model.Address;
import jpa.jpaworkshop.model.EmployeeType;
import jpa.jpaworkshop.model.entity.*;
import jpa.jpaworkshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EntryCardRepository entryCardRepository;
    private final FloorRepository floorRepository;
    private final SupervisorRepository supervisorRepository;

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

        Floor floor1 = new Floor();
        Floor floor2 = new Floor();
        Floor floor3 = new Floor();
        floor1.setLevelNumber(1);
        floor2.setLevelNumber(2);
        floor3.setLevelNumber(3);
        floorRepository.saveAll(Arrays.asList(floor1,floor2,floor3));

        EntryCard entryCard1 = new EntryCard();
        EntryCard entryCard2 = new EntryCard();
        EntryCard entryCard3 = new EntryCard();
        EntryCard entryCard4 = new EntryCard();
        EntryCard entryCard5 = new EntryCard();
        EntryCard entryCard6 = new EntryCard();
        EntryCard entryCard7 = new EntryCard();
        EntryCard entryCard8 = new EntryCard();
        EntryCard entryCard9 = new EntryCard();
        EntryCard entryCard10 = new EntryCard();
        EntryCard entryCard11 = new EntryCard();
        entryCard1.setUuid(UUID.randomUUID());
        entryCard2.setUuid(UUID.randomUUID());
        entryCard3.setUuid(UUID.randomUUID());
        entryCard4.setUuid(UUID.randomUUID());
        entryCard5.setUuid(UUID.randomUUID());
        entryCard6.setUuid(UUID.randomUUID());
        entryCard7.setUuid(UUID.randomUUID());
        entryCard8.setUuid(UUID.randomUUID());
        entryCard9.setUuid(UUID.randomUUID());
        entryCard10.setUuid(UUID.randomUUID());
        entryCard11.setUuid(UUID.randomUUID());
        entryCard1.setFloorAccess(Set.of(floor1, floor2));
        entryCard2.setFloorAccess(Set.of(floor3, floor2));
        entryCard3.setFloorAccess(Set.of(floor1, floor3));
        entryCard4.setFloorAccess(Set.of(floor1));
        entryCard5.setFloorAccess(Set.of(floor1, floor2, floor3));
        entryCard6.setFloorAccess(Set.of(floor2, floor3));
        entryCard7.setFloorAccess(Set.of(floor1, floor3));
        entryCard8.setFloorAccess(Set.of(floor1, floor2));
        entryCard9.setFloorAccess(Set.of(floor1, floor2, floor3));
        entryCard10.setFloorAccess(Set.of(floor3));
        entryCard11.setFloorAccess(Set.of(floor2));
        entryCardRepository.saveAll(Arrays.asList(entryCard1,entryCard2,entryCard3,entryCard4,entryCard5,entryCard6,entryCard7,entryCard8,entryCard9,entryCard10,entryCard11));

        Supervisor supervisor1 = new Supervisor();
        supervisor1.setFirstName("John");
        supervisor1.setLastName("Doe");
        supervisor1.setSalary(BigDecimal.valueOf(10000));
        supervisor1.setEmployeeType(EmployeeType.FULL_TIME);

        Supervisor supervisor2 = new Supervisor();
        supervisor2.setFirstName("Jane");
        supervisor2.setLastName("Doe");
        supervisor2.setSalary(BigDecimal.valueOf(12000));
        supervisor2.setEmployeeType(EmployeeType.FULL_TIME);

        Supervisor supervisor3 = new Supervisor();
        supervisor3.setFirstName("Mark");
        supervisor3.setLastName("Smith");
        supervisor3.setSalary(BigDecimal.valueOf(15000));
        supervisor3.setEmployeeType(EmployeeType.FULL_TIME);

        Supervisor supervisor4 = new Supervisor();
        supervisor4.setFirstName("Anna");
        supervisor4.setLastName("Jones");
        supervisor4.setSalary(BigDecimal.valueOf(11000));
        supervisor4.setEmployeeType(EmployeeType.CONTRACTOR);

        Supervisor supervisor5 = new Supervisor();
        supervisor5.setFirstName("Peter");
        supervisor5.setLastName("Wilson");
        supervisor5.setSalary(BigDecimal.valueOf(14000));
        supervisor5.setEmployeeType(EmployeeType.CONTRACTOR);
        supervisorRepository.saveAll(Arrays.asList(supervisor1,supervisor2,supervisor3,supervisor4,supervisor5));

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
        employee3.setDepartment(department3);
        employee3.setEntryCard(entryCard3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Tom");
        employee4.setLastName("Smith");
        employee4.setSalary(new BigDecimal("5000"));
        employee4.setDepartment(department1);
        employee4.setSupervisor(supervisor1);
        employee4.setContractEnd(LocalDate.of(2023, 12, 31));
        employee4.setEntryCard(entryCard4);


        Employee employee5 = new Employee();
        employee5.setFirstName("Mary");
        employee5.setLastName("Johnson");
        employee5.setSalary(new BigDecimal("7500"));
        employee5.setDepartment(department1);
        employee5.setSupervisor(supervisor1);
        employee5.setContractEnd(LocalDate.of(2024, 6, 30));
        employee5.setEntryCard(entryCard5);

        Employee employee6 = new Employee();
        employee6.setFirstName("John");
        employee6.setLastName("Doe");
        employee6.setSalary(new BigDecimal("6500.00"));
        employee6.setDepartment(department2);
        employee6.setSupervisor(supervisor2);
        employee6.setContractEnd(LocalDate.of(2025, 1, 15));
        employee6.setEntryCard(entryCard6);

        Employee employee7 = new Employee();
        employee7.setFirstName("Jane");
        employee7.setLastName("Smith");
        employee7.setSalary(new BigDecimal("7500.25"));
        employee7.setDepartment(department3);
        employee7.setSupervisor(supervisor3);
        employee7.setContractEnd(LocalDate.of(2023, 12, 31));
        employee7.setEntryCard(entryCard7);

        Employee employee8 = new Employee();
        employee8.setFirstName("Robert");
        employee8.setLastName("Brown");
        employee8.setSalary(new BigDecimal("4500.75"));
        employee8.setDepartment(department4);
        employee8.setSupervisor(supervisor4);
        employee8.setContractEnd(LocalDate.of(2022, 11, 1));
        employee8.setEntryCard(entryCard8);

        Employee employee9 = new Employee();
        employee9.setFirstName("Samantha");
        employee9.setLastName("Lee");
        employee9.setSalary(new BigDecimal("8000.00"));
        employee9.setDepartment(department5);
        employee9.setSupervisor(supervisor5);
        employee9.setContractEnd(LocalDate.of(2026, 7, 31));
        employee9.setEntryCard(entryCard9);

        Employee employee10 = new Employee();
        employee10.setFirstName("Michael");
        employee10.setLastName("Wang");
        employee10.setSalary(new BigDecimal("6200.50"));
        employee10.setDepartment(department1);
        employee10.setSupervisor(supervisor1);
        employee10.setContractEnd(LocalDate.of(2024, 9, 30));
        employee10.setEntryCard(entryCard10);

        Employee employee11 = new Employee();
        employee11.setFirstName("Emily");
        employee11.setLastName("Jones");
        employee11.setSalary(new BigDecimal("7100.25"));
        employee11.setDepartment(department3);
        employee11.setSupervisor(supervisor3);
        employee11.setContractEnd(LocalDate.of(2023, 6, 30));
        employee11.setEntryCard(entryCard11);

        employeeRepository.saveAll(Arrays.asList(employee1,employee2,employee3,employee4,employee5,employee6,employee7,employee8,employee9,employee10,employee11));  //spring data jpa nie potrzebuje specjalnego obsługiwania sesji!! sam się tym zajmuje interfejs RepositoeyJpa


    }

}
