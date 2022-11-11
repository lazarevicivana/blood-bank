package ftn.uns.ac.rs.bloodbank.complains;

import ftn.uns.ac.rs.bloodbank.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Complain")
@Table(name = "complain")
@Getter
@Setter
public class Complain {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;
    private String comment;
    @Enumerated(EnumType.STRING)
    private ComplainStatus complainStatus = ComplainStatus.PENDING;

}
