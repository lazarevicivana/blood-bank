package ftn.uns.ac.rs.bloodbank.appointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppCustomer {
	private UUID id;
	private AppointmentStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime finishTime;
	private UUID centerId;
	private String centerName;
}
