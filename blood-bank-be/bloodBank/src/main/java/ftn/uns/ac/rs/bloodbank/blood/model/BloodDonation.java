package ftn.uns.ac.rs.bloodbank.blood.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "BloodDonation")
@Table(name = "blood_donation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodDonation {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private Integer bloodUnit;
    @Enumerated(EnumType.STRING)
    private BloodType  bloodType;
    private String noteForDoctor;
}
