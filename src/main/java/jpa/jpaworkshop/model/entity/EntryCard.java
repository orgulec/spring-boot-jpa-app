package jpa.jpaworkshop.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EntryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @ManyToMany
    @JoinTable(name = "floor_access",
            joinColumns = @JoinColumn(name = "entry_card_id"),
            inverseJoinColumns = @JoinColumn(name = "froor_id")
    )
    private Set<Floor> floorAccess; //kolumna łącząca 2 tabele n : n

    @Override
    public String toString() {
        return "EntryCard{" +
                "id=" + id +
                ", uuid=" + uuid +
                '}';
    }
}
