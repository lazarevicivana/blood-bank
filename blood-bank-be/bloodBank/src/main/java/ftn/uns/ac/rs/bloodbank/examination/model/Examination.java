package ftn.uns.ac.rs.bloodbank.examination.model;

import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodDonation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Examination")
@Table(name = "examination")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Examination {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment", referencedColumnName = "id")
    private ScheduleAppointment appointment;
    private Boolean isAppeared;
    private Boolean isSuitableBloodDonor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_donation", referencedColumnName = "id")
    private BloodDonation bloodDonation;

}
