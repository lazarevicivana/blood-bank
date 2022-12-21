package ftn.uns.ac.rs.bloodbank.appointment.controller;

import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentResponse;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import ftn.uns.ac.rs.bloodbank.center.service.CenterService;
import ftn.uns.ac.rs.bloodbank.mapper.AppointmentMapper;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.mapper.ScheduleAppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/scheduledAppointments")
@AllArgsConstructor
public class ScheduleAppointmentController {
    private final ScheduleAppointmentService scheduleAppointmentService;
    private final ScheduleAppointmentMapper appointmentMapper;



    @PostMapping()
    public ResponseEntity<String> createScheduleAppointment(@RequestBody ScheduleAppointment scheduleAppointment){
        scheduleAppointmentService.createScheduleAppointment(scheduleAppointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ScheduleAppointmentResponse>> getAllScheduleAppointments(){
        var appointments = scheduleAppointmentService.getAll()
                .stream()
                .map(appointmentMapper::ScheduleToScheduleResponseDto)
                .toList();
        return ResponseEntity.ok(appointments);
    }
}
