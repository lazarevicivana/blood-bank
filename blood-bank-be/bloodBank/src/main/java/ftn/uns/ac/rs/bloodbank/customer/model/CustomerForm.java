package ftn.uns.ac.rs.bloodbank.customer.model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "CustomerForm")
@Table(name = "customer_form")
@Getter
@Setter
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
    private Boolean isUnderTherapy;
    private Boolean isBloodPressureNormal;
    private Boolean isDentis;
    private Boolean isPiercingTattoo;

    private Date submissionDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;
}
