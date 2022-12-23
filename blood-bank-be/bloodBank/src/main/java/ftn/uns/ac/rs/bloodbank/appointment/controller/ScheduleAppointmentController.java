package ftn.uns.ac.rs.bloodbank.appointment.controller;
import ftn.uns.ac.rs.bloodbank.appointment.dto.*;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.mapper.ScheduleAppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/schedule-appointments")
public class ScheduleAppointmentController {
    private final ScheduleAppointmentService _scheduleAppointmentService;
    private final ScheduleAppointmentMapper appointmentMapper;

    @PostMapping()
     @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<String> createAppointment(@RequestBody ScheduleAppointmentRequest appointmentRequest){
        _scheduleAppointmentService.createScheduleAppointment(appointmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<ScheduleAppointmentResponse>> getAllScheduleAppointments(){
        var appointments = _scheduleAppointmentService.getAll()
                .stream()
                .map(appointmentMapper::ScheduleToScheduleResponseDto)
                .toList();
        return ResponseEntity.ok(appointments);
    }
    @GetMapping(path = "/examination/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public ResponseEntity<ScheduleAppointmentExaminationDto> getScheduledAppointmentForExamination(@PathVariable("id") @NotNull UUID id){
        var app = _scheduleAppointmentService.getScheduledAppointment(id);
        var mappedAppointment = appointmentMapper.ScheduleToScheduleExaminationDto(app);
        return ResponseEntity.ok(mappedAppointment);
    }
    @GetMapping(path = "/center/{id}")
    public ResponseEntity<List<ScheduleAppResponse>> findScheduleAppointmentsCenterId(@PathVariable("id") @NotNull UUID id){
        var app = _scheduleAppointmentService.findScheduleAppointmentsCenterId(id);
        var appointments = app
                .stream()
                .map(appointmentMapper::ScheduleToScheduleAppResponse)
                .toList();
        return ResponseEntity.ok(appointments);
    }
    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<List<ScheduleAppCustomer>> findScheduleAppointmentsCustomerId(@PathVariable("id") @NotNull UUID id){
        var app = _scheduleAppointmentService.findScheduleAppointmentsCustomerId(id);
        var appointments = app
                .stream()
                .map(appointmentMapper::ScheduleToScheduleCustomerAppResponse)
                .toList();
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> cancelScheduledAppointment(@PathVariable("id") @NotNull UUID id){
        _scheduleAppointmentService.cancelScheduledAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
