package ftn.uns.ac.rs.bloodbank.blood;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "Blood")
@Table(name = "blood")
public class Blood {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private BloodType  bloodType;
    private Integer bloodUnit;
}
