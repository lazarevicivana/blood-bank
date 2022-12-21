package ftn.uns.ac.rs.bloodbank.appointment.model;

import com.fasterxml.jackson.databind.SerializationFeature;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "ScheduleAppointment")
@Table(name = "schedule_appointment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAppointment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentId", referencedColumnName = "id")
    private Appointment appointment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

//    @Enumerated(EnumType.STRING)
//    private AppointmentStatus status = AppointmentStatus.PENDING;
}
