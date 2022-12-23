package ftn.uns.ac.rs.bloodbank.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentExaminationDto implements Serializable {
    private UUID centerId;
    private UUID customerId;
}
