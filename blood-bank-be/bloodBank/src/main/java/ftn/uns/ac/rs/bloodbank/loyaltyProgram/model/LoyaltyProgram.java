package ftn.uns.ac.rs.bloodbank.loyaltyProgram.model;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "LoyaltyProgram")
@Table(name = "loyalty_program")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyProgram {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "predefined_loyalty_program_id",referencedColumnName = "id")
    private PredefinedLoyaltyProgram loyaltyProgram;
    private Integer currentPoints;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private ApplicationUser customer;
}
