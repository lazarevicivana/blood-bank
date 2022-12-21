package ftn.uns.ac.rs.bloodbank.appointment.dto;

import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerResponse;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentRequest {
    private UUID appointmentId;
    private UUID customerId;
}
