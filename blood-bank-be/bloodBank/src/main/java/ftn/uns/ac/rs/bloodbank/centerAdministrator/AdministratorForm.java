package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.appointment.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.blood.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "AdministratorForm")
@Table(name = "administrator_form")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministratorForm {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @OneToOne
    private ScheduleAppointment scheduleAppointment;
    private BloodType bloodType;
    private Integer quantity;
}
