package ftn.uns.ac.rs.bloodbank.appointment;

import ftn.uns.ac.rs.bloodbank.customer.Customer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class ScheduleAppointment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private Appointment appointment;
    private Customer customer;
}
