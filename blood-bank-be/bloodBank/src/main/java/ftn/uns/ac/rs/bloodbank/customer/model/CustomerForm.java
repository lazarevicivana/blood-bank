package ftn.uns.ac.rs.bloodbank.customer.model;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "CustomerForm")
@Table(name = "customer_form")
public class CustomerForm {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private Boolean isAge;
    private Boolean isWeight;
    private Boolean hadTransfusion;
    private Boolean hadCancer;
    private Boolean isSick;
    private Boolean isPregnant;
    private Boolean onPeriod;
    private Boolean isSexual;
    private Boolean isAllergic;
    private Boolean useMedication;
    private UUID customerId;
    private Date submissionDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

}
