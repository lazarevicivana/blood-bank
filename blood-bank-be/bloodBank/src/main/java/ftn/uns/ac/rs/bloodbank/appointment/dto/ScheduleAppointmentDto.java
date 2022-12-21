package ftn.uns.ac.rs.bloodbank.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentDto {
    private UUID appointmentId;
    private UUID customerId;
}
