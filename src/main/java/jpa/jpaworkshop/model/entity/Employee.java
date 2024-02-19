package jpa.jpaworkshop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)   //wykluczamy ID z settera
    private Long id;

    @Column(nullable = false)   // not null -> możliwość definiowania parametrów kolumny jak w mysql
    private String firstName;
    private String lastName;

//    @Column(name = "zarobki")   // opcjonalna zmiana nazwy kolumny w tabeli sql
    private BigDecimal salary;
    private LocalDate contractEnd;

    @ManyToOne  //dołączenie wielu employee do 1 department
    @JoinColumn(name = "dep_id")    //łączenie kolumn jako ...
    @JsonBackReference  //referencja wsteczna JSON dla dobrego podłączenia referencji do tabeli Department
    private Department department;  //foreign key

    @OneToOne   //relacja 1:1
    private EntryCard entryCard;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    @JsonBackReference
    private Supervisor supervisor;

}
