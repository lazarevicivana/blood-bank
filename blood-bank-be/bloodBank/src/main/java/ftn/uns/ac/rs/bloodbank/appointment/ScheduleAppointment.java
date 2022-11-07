package ftn.uns.ac.rs.bloodbank.appointment;

import ftn.uns.ac.rs.bloodbank.customer.Customer;

import javax.persistence.*;
import java.util.UUID;

public class ScheduleAppointment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private Appointment appointment;
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;
}
