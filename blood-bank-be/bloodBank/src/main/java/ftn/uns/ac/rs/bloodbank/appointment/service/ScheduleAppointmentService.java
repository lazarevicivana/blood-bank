package ftn.uns.ac.rs.bloodbank.appointment.service;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.appointment.model.AppointmentStatus;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerFormService;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerService;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScheduleAppointmentService {
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;
    private final QRGeneratorService qrGeneratorService;
    private final AppointmentService appointmentService;
    private final CustomerFormService customerFormService;
    private final CustomerService customerService;
    private static final String QR_FILE_PATH = "./src/main/resources/QR/qr-code.png";
    @Transactional
    public void createScheduleAppointment(ScheduleAppointmentRequest request)
    {
        var appointment = appointmentService.findByID(request.getAppointment_id());
        validateAppointmentDateTime(appointment);
        var customer = customerService.getById(request.getCustomer_id());
        if(!checkIfDonatingIsPossible(customer.getId()))
            throw new ApiBadRequestException("You are have already donated blood in the last six months!");
       customerFormService.checkIfQuestionnaireIsFilledNow(customer.getId());
       appointmentService.UpdateAppointmentDelete(appointment.getId());
        var scheduleAppointment = ScheduleAppointment
                .builder()
                .appointment(appointment)
                .customer(customer)
                .status(AppointmentStatus.PENDING)
                .build();
        scheduleAppointmentRepository.save(scheduleAppointment);
        generateQRCodeAndSendEmail(scheduleAppointment);
    }

    private boolean checkIfDonatingIsPossible(UUID id) {
        var appointments = scheduleAppointmentRepository.findScheduleAppointmentsCustomerId(id);
        var currentDate = LocalDateTime.now();
        var currentDateMinus6Months = currentDate.minusMonths(6);
        if(appointments.isEmpty())
            return true;
       var lastAppointments =  appointments.stream()
                .filter(x -> x.getStatus() == AppointmentStatus.ACCEPTED
                        && x.getAppointment().getDate()
                                            .isAfter(currentDateMinus6Months)).toList();
        return lastAppointments.isEmpty();
    }

    private static void validateAppointmentDateTime(Appointment appointment) {
        if( !appointment.isValidDate()){
            throw new ApiBadRequestException("Date is invalid");
        }
        if(!appointment.isValidDateTime())
        {
            throw new ApiBadRequestException("Time is invalid");
        }
    }

    private void generateQRCodeAndSendEmail(ScheduleAppointment scheduleAppointment) {
        try {
            qrGeneratorService.generateQRCodeImage(scheduleAppointment, 200, 200, QR_FILE_PATH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ScheduleAppointment getScheduledAppointment(UUID id){
        var app = scheduleAppointmentRepository.findById(id)
                .orElseThrow(()-> new ApiNotFoundException("Appointment not found"));
        return app;
    }
    public List<ScheduleAppointment> findScheduleAppointmentsCenterId(UUID centerId){
        return scheduleAppointmentRepository.findScheduleAppointmentsCenterId(centerId);
    }
    public List<ScheduleAppointment> findScheduleAppointmentsCustomerId(UUID customerId){
        return scheduleAppointmentRepository.findScheduleAppointmentsCustomerId(customerId);
    }

    public List<ScheduleAppointment> getAll(){
        return scheduleAppointmentRepository.findAll();
    }
}
