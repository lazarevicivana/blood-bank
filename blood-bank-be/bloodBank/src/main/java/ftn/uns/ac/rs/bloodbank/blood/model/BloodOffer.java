package ftn.uns.ac.rs.bloodbank.blood.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "BloodOffer")
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
    @JoinColumn(name = "center_id")
    private Center center;
    @ManyToOne
    @JoinColumn(name = "blood_contract_id")
    private BloodContract bloodContract;
    private Date offerDate;
    private boolean isSended;

}
