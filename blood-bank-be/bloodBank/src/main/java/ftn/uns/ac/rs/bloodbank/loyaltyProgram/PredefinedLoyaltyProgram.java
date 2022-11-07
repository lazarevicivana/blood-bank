package ftn.uns.ac.rs.bloodbank.loyaltyProgram;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "PredefinedLoyaltyProgram")
@Table(name = "predefined_loyalty_program")
public class PredefinedLoyaltyProgram {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private CustomerCategory customerCategory;
    private Integer numberOfPoints;
}
