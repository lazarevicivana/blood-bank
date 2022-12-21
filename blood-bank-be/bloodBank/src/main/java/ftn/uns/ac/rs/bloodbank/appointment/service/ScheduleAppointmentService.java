package ftn.uns.ac.rs.bloodbank.appointment.service;

import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleAppointmentService {
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;

    public void createScheduleAppointment(ScheduleAppointment scheduleAppointment){
        scheduleAppointmentRepository.save(scheduleAppointment);
    }

    public List<ScheduleAppointment> getAll(){
        return scheduleAppointmentRepository.findAll();
    }
}
