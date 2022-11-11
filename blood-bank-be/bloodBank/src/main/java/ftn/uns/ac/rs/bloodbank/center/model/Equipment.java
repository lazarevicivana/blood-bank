package ftn.uns.ac.rs.bloodbank.center.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
@Entity(name = "Equipment")
@Table(name = "equipment", uniqueConstraints= {
        @UniqueConstraint(
                columnNames = {"name"}
        )})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private Integer quantity;
    private String name;
    @ManyToMany
    private Set<Center> centers;

}
