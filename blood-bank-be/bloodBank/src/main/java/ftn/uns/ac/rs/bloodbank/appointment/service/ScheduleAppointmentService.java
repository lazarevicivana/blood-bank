package ftn.uns.ac.rs.bloodbank.appointment.service;

import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ScheduleAppointmentService {
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;
    private final QRGeneratorService qrGeneratorService;
    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private static final String QR_FILE_PATH = "./src/main/resources/QR/qr-code.png";
    @Transactional
    public void scheduleAppointment(ScheduleAppointmentRequest request)
    {
        var appointment = appointmentService.findByID(request.getAppointmentId());
        var customer = customerService.getById(request.getCustomerId());
        var scheduleAppointment = ScheduleAppointment
                .builder()
                .appointment(appointment)
                .customer(customer)
                .status(AppointmentStatus.PENDING)
                .build();
        scheduleAppointmentRepository.save(scheduleAppointment);
        generateQRCodeAndSendEmail(scheduleAppointment);
    }

    private void generateQRCodeAndSendEmail(ScheduleAppointment scheduleAppointment) {
        try {
           qrGeneratorService.generateQRCodeImage(scheduleAppointment,200,200,QR_FILE_PATH);
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
}
