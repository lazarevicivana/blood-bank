package ftn.uns.ac.rs.bloodbank.appointment.service;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.AppointmentRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdminRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CenterAdminRepository centerAdminRepository;
    private final CenterRepository centerRepository;
    @Transactional
    public void createAppointment(AppointmentRequest appointmentRequest){
        var appointment = Appointment
                .builder()
                .startTime(appointmentRequest.getStartTime())
                .finishTime(appointmentRequest.getFinishTime())
                .date(appointmentRequest.getDate())
                .deleted(false)
                .build();
        var staff =appointmentRequest.getMedical_stuff().stream()
                        .map(uuid -> {
                            var admin = new CenterAdministrator();
                            if(centerAdminRepository.findById(uuid).isPresent()){
                                admin =centerAdminRepository.findById(uuid).get();
                                System.out.println(admin.getUsername());
                            }
                            return admin;
                        })
                        .collect(Collectors.toSet());
        appointment.setMedicalStaffs(staff);
        var center =centerRepository.findById(appointmentRequest.getCenterId()).orElseThrow(() -> new ApiBadRequestException("Center doesnt exist!"));
        center.addAppointment(appointment);
        appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllAppointmentsForCenter(UUID centerId){
        return appointmentRepository.getAllAppointmentsForCenter(centerId);
    }
    public List<CenterAdministrator> getMedicalStaffsForAppointment(UUID appointmentId){
        return appointmentRepository.getMedicalStaffsForAppointment(appointmentId);
    }
}
