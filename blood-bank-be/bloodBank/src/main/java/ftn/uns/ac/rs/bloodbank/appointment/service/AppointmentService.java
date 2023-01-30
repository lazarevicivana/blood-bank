package ftn.uns.ac.rs.bloodbank.appointment.service;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentRequest;
import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.AppointmentRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdminRepository;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CenterAdminRepository centerAdminRepository;
    private final CenterRepository centerRepository;
    @Transactional
    public void createAppointment(AppointmentRequest appointmentRequest){
        if (appointmentRequest == null) {
                throw new ApiBadRequestException("Bad request appointment is null!");
            }
        try {
            var center = centerRepository.performLock(appointmentRequest.getCenterId());
            if (center == null) {
                throw new PessimisticLockingFailureException("The record is locked by another user.");
            }
            var appointment = Appointment
                    .builder()
                    .startTime(appointmentRequest.getStartTime())
                    .finishTime(appointmentRequest.getFinishTime())
                    .date(appointmentRequest.getDate())
                    .deleted(false)
                    .build();
            Set<CenterAdministrator> staff = getCenterAdministrators(appointmentRequest);
            checkStaffAvailability(appointmentRequest);
            appointment.setMedicalStaffs(staff);
            center.addAppointment(appointment);
            validateDate(appointment);
            isOverlappingDate(appointment);
            appointmentRepository.save(appointment);
        } catch (PessimisticLockingFailureException ex) {
        throw new PessimisticLockingFailureException("The record is locked by another user.");
    }
    }
    @Transactional
    public void checkStaffAvailability(AppointmentRequest appointmentRequest){
        Set<CenterAdministrator> staff = getCenterAdministrators(appointmentRequest);
        var appointments = appointmentRepository.getAllAppointmentsForCenter(appointmentRequest.getCenterId());
        for (CenterAdministrator admin:staff ) {
            for (Appointment appointment:appointments) {
                if(!checkAppointmentTime(appointmentRequest,appointment)){
                    for (CenterAdministrator administrator: appointment.getMedicalStaffs()) {
                        if(admin.getId() == administrator.getId()){
                            throw new ApiBadRequestException("Doctor "+admin.getName()+ " "+ admin.getSurname() + " is not available in selected time.");
                        }
                    }
                }
            }
        }

    }

    private boolean checkAppointmentTime(AppointmentRequest appointmentRequest,Appointment appointment){
        return appointmentRequest.getStartTime().isAfter(appointment.getFinishTime()) || appointmentRequest.getFinishTime().isBefore(appointment.getStartTime());
    }
    private Set<CenterAdministrator> getCenterAdministrators(AppointmentRequest appointmentRequest) {
        return appointmentRequest.getMedicalStaffs().stream()
                        .map(uuid -> centerAdminRepository.findById(uuid).orElseThrow(() ->
                                new ApiBadRequestException("Wrong staff id provided!")))
                        .collect(Collectors.toSet());
    }
    @Cacheable("appointments-for-center")
    public List<Appointment> getAllAppointmentsForCenter(UUID centerId){
        return appointmentRepository.getAllAppointmentsForCenter(centerId);
    }
    @Cacheable("future-appointments")
    public List<Appointment> getFutureAppointments(UUID centerId){
        var currentDate = LocalDateTime.now();
        return appointmentRepository.getAllAppointmentsForCenter(centerId)
                .stream().filter(a -> a.getDate().isAfter(currentDate)
                        && !a.getDeleted()).toList();
    }

    public Appointment getAppointmentsForCenter(LocalDateTime selectedTime, UUID id){
        var appointments = appointmentRepository.getAllAppointmentsForCenter(id);
        for (var appointment: appointments) {
            if(checkAppointmentTime(appointment,selectedTime)){
                return appointment;
            }
        }

        throw new ApiNotFoundException("Center doesnt have selected appointment");
    }
    public boolean checkAppointmentTime(Appointment appointment, LocalDateTime selectedTime){
        if(appointment.getDate().getYear() == selectedTime.getYear() && appointment.getDate().getDayOfMonth() == selectedTime.getDayOfMonth()
                && appointment.getDate().getMonth() == selectedTime.getMonth()
                && (appointment.getStartTime().isBefore(selectedTime.toLocalTime()) || appointment.getStartTime().equals(selectedTime.toLocalTime())) && (appointment.getFinishTime().isAfter(selectedTime.toLocalTime()) || appointment.getFinishTime().equals(selectedTime.toLocalTime())) ){
            return true;
        }
        return false;
    }

    public List<CenterAdministrator> getMedicalStaffsForAppointment(UUID appointmentId){
        return appointmentRepository.getMedicalStaffsForAppointment(appointmentId);
    }
    private void validateDate(Appointment appointment){
        if(appointment.isValidDate()){
            throw new ApiBadRequestException("Please select upcoming date!");
        }
        if(appointment.isValidDateTime()){
            throw new ApiBadRequestException("Wrong start time and end time range!");
        }
    }
    private void isOverlappingDate(Appointment appointment){
        var appointments = this.appointmentRepository
                .getAllAppointmentsForCenter(appointment.getCenter().getId());
        var isOverlapping = appointments.stream().anyMatch(appointment::isOverlappingDate);
        if(isOverlapping){
            throw new ApiBadRequestException("Appointment date overlaps with other appointments date!");
        }
    }
    public Appointment findByID(UUID appointmentId){
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ApiNotFoundException("Appointment not found"));
    }

    public void UpdateAppointmentDelete(UUID id) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Appointment not found!"));
        if(appointment.getDeleted())
            appointment.setDeleted(false);
        else
            appointment.setDeleted(true);
        appointmentRepository.save(appointment);
    }
}
