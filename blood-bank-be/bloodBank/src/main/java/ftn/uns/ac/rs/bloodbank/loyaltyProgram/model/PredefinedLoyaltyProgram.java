package ftn.uns.ac.rs.bloodbank.loyaltyProgram.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "PredefinedLoyaltyProgram")
@Table(name = "predefined_loyalty_program")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedLoyaltyProgram {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerCategory customerCategory;
    private Integer numberOfPoints;

    private String loyaltyConvenience;
}
