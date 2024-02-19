package jpa.jpaworkshop.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jpa.jpaworkshop.model.Address;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;

//    @Transient  //adnotacja usuwająca pole (kolumnę) z tabeli w DB
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "department") //sposób mapowania listy na tabelę
    @JsonManagedReference   //zarządzanie referencją JSON tabeli Employee !!!
    private List<Employee> employees = new ArrayList<>();


}
