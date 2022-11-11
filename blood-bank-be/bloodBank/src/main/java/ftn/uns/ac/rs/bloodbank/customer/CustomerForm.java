package ftn.uns.ac.rs.bloodbank.customer;

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
    private Integer age;
    private Integer weight;
    private Boolean hadTransfusion;
    private Boolean hadCancer;
    private Date submissionDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

}
