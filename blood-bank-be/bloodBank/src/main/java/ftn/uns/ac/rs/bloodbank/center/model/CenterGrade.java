package ftn.uns.ac.rs.bloodbank.center.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.customer.Customer;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "CenterGrade")
@Table(name = "center_grade")
public class CenterGrade {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    private Center center;
    @ManyToOne
    private Customer customer;
    private Integer grade;
}
