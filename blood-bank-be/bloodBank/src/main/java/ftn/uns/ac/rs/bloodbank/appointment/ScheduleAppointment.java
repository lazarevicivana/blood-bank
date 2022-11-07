package ftn.uns.ac.rs.bloodbank.appointment;

import ftn.uns.ac.rs.bloodbank.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "ScheduleAppointment")
@Table(name = "schedule_appointment")
@Getter
@Setter
public class ScheduleAppointment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @OneToOne
    private Appointment appointment;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;
}
