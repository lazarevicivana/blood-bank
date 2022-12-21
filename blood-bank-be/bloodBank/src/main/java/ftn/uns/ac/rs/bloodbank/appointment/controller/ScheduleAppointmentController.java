package ftn.uns.ac.rs.bloodbank.appointment.controller;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentDto;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/schedule-appointment")
public class ScheduleAppointmentController {
    private final ScheduleAppointmentService _scheduleAppointmentService;
    private final MapperService _mapperService;
    @PostMapping(path = "schedule-appointment")
    public ResponseEntity<String> createAppointment(@RequestBody ScheduleAppointmentDto appointmentRequest){
        _scheduleAppointmentService.scheduleAppointment(appointmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
