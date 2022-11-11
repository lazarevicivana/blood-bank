package ftn.uns.ac.rs.bloodbank.loyaltyProgram;

import ftn.uns.ac.rs.bloodbank.customer.Customer;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "LoyaltyProgram")
@Table(name = "loyalty_program")
public class LoyaltyProgram {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    private PredefinedLoyaltyProgram loyaltyProgram;
    private Integer currentPoints;
    @OneToOne
    private Customer customer;
}
