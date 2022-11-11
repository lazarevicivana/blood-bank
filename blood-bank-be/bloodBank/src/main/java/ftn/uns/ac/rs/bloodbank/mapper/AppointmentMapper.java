package ftn.uns.ac.rs.bloodbank.mapper;

import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.MedicalStaffResponse;
import ftn.uns.ac.rs.bloodbank.appointment.model.Appointment;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }
    public AppointmentResponse AppointmentToAppointmentDto(Appointment appointment){
        return modelMapper.map(appointment,AppointmentResponse.class);
    }
    public MedicalStaffResponse MedicalStaffToAppUserDto(CenterAdministrator applicationUser){
        return modelMapper.map(applicationUser, MedicalStaffResponse.class);
    }

}
