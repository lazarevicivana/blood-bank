package ftn.uns.ac.rs.bloodbank.mapper;

import ftn.uns.ac.rs.bloodbank.appointment.dto.MedicalStaffResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.ScheduleAppointmentResponse;
import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ScheduleAppointmentMapper {

    private final ModelMapper modelMapper;

    public ScheduleAppointmentMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public ScheduleAppointmentResponse ScheduleToScheduleResponseDto(ScheduleAppointment scheduleAppointment){
        return modelMapper.map(scheduleAppointment, ScheduleAppointmentResponse.class);
    }
}
