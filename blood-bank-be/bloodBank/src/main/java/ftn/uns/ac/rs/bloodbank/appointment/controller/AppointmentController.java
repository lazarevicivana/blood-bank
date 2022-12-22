package ftn.uns.ac.rs.bloodbank.appointment.controller;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentResponse;
import ftn.uns.ac.rs.bloodbank.appointment.service.AppointmentService;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.mapper.AppointmentMapper;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;
    @PostMapping()
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequest appointmentRequest){
        appointmentService.createAppointment(appointmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/center/{centerId}")
    List<AppointmentResponse> getAllAppointmentsForCenter(@NotNull @PathVariable("centerId") UUID centerId){
        return appointmentService.getAllAppointmentsForCenter(centerId).stream()
                .map(appointment -> {
                    var staffs = appointmentService.getMedicalStaffsForAppointment(appointment.getId()).stream()
                            .map(appointmentMapper::MedicalStaffToAppUserDto)
                            .toList();
                    var app=  appointmentMapper.AppointmentToAppointmentDto(appointment);
                    app.setMedicalStaffs(staffs);
                    return app;
                })
                .toList();
    }

    @PostMapping(path = "/appointment-of-center/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentOfCenter(@RequestBody LocalDateTime selectedTime, @NotNull @PathVariable("id") UUID id){
        var center = appointmentMapper.AppointmentToAppointmentDto(appointmentService.getAppointmentOfCenter(selectedTime,id));
        return ResponseEntity.ok(center);
    }
}
