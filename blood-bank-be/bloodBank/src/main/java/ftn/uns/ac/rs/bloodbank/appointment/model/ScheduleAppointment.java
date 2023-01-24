package ftn.uns.ac.rs.bloodbank.appointment.model;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
@Entity(name = "ScheduleAppointment")
@Table(name = "schedule_appointment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAppointment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;
}
