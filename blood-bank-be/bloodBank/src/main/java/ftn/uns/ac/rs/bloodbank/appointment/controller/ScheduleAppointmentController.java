package ftn.uns.ac.rs.bloodbank.appointment.controller;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentResponse;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.mapper.ScheduleAppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/schedule-appointments")
public class ScheduleAppointmentController {
    private final ScheduleAppointmentService _scheduleAppointmentService;
    private final ScheduleAppointmentMapper appointmentMapper;

    @PostMapping()
    public ResponseEntity<String> createAppointment(@RequestBody ScheduleAppointmentRequest appointmentRequest){
        _scheduleAppointmentService.createScheduleAppointment(appointmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ScheduleAppointmentResponse>> getAllScheduleAppointments(){
        var appointments = _scheduleAppointmentService.getAll()
                .stream()
                .map(appointmentMapper::ScheduleToScheduleResponseDto)
                .toList();
        return ResponseEntity.ok(appointments);
    }

}
