package ftn.uns.ac.rs.bloodbank.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "Profession")
@Table(name="profession")
public class Profession {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private ProfessionStatus professionStatus;
    private String professionDescription;
}
