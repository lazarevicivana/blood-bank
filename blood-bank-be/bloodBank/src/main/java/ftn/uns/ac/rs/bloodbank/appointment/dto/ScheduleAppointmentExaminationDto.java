package ftn.uns.ac.rs.bloodbank.appointment.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ScheduleAppointmentExaminationDto implements Serializable {
    private final UUID id;
}
