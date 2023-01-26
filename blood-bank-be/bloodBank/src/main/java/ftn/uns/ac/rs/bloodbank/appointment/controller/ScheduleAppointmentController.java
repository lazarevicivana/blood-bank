package ftn.uns.ac.rs.bloodbank.appointment.controller;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import ftn.uns.ac.rs.bloodbank.appointment.dto.*;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.service.QRGeneratorService;
import ftn.uns.ac.rs.bloodbank.appointment.service.ScheduleAppointmentService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.mapper.ScheduleAppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.qrcode.QRCodeReader;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/schedule-appointments")
public class ScheduleAppointmentController {
    private final ScheduleAppointmentService _scheduleAppointmentService;
    private final ScheduleAppointmentMapper appointmentMapper;

    private final QRGeneratorService qrGeneratorService;

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
    @GetMapping(path = "/accepted/{id}")
    public ResponseEntity<List<ScheduleAppCustomer>> findPassedScheduleAppointmentsCustomerId(@PathVariable("id") @NotNull UUID id){
        var app = _scheduleAppointmentService.findPassedScheduleAppointmentsByCustomerId(id);
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


    @PostMapping("/uploadAppointmentQR")
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public ResponseEntity<AppointmentIdResponse> getAppointmentFromQrCode(@RequestParam("file") MultipartFile file) throws Exception {
        UUID qrCodeText = UUID.fromString(qrGeneratorService.handleFileUpload(file));
        AppointmentIdResponse response = new AppointmentIdResponse();
        response.id = qrCodeText;
        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointment/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public ResponseEntity<AppointmentIdResponse> findScheduleAppointmentsByAppointmentId(@PathVariable("id") @NotNull UUID id){
        var app = _scheduleAppointmentService.findScheduleAppointmentsByAppointmentId(id);
        AppointmentIdResponse response = new AppointmentIdResponse();
        response.id = app.getId();
        return ResponseEntity.ok(response);
    }


}
