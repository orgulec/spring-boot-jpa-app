package jpa.jpaworkshop.model;

import jakarta.persistence.Embeddable;
import lombok.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class Address {
    private String country;
    private String city;
    private String street;
    private String zipCode;
}
