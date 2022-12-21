package ftn.uns.ac.rs.bloodbank.blood.model;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "BloodBank")
@Table(name = "blood_bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodBank {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private BloodType  bloodType;
    private Integer bloodUnit;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center", referencedColumnName = "id")
    private Center center;
}
