package ftn.uns.ac.rs.bloodbank.blood.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "blood_offer")
@Table(name = "blood_offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;
    @ManyToOne
    @JoinColumn(name = "blood_contract_id")
    private BloodContract bloodContract;
    private Date offerDate;

}
