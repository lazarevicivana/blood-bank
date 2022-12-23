package ftn.uns.ac.rs.bloodbank.mapper;

import ftn.uns.ac.rs.bloodbank.appointment.dto.*;
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
    public ScheduleAppResponse ScheduleToScheduleAppResponse(ScheduleAppointment scheduleAppointment){
        return modelMapper.map(scheduleAppointment, ScheduleAppResponse.class);
    }
    public ScheduleAppCustomer ScheduleToScheduleCustomerAppResponse(ScheduleAppointment scheduleAppointment){
        return modelMapper.map(scheduleAppointment, ScheduleAppCustomer.class);
    }
    public ScheduleAppointmentExaminationDto ScheduleToScheduleExaminationDto(ScheduleAppointment scheduleAppointment){
        return modelMapper.map(scheduleAppointment, ScheduleAppointmentExaminationDto.class);
    }
    public ScheduleAppointment ScheduleRequestDtoToSchedule(ScheduleAppointmentRequest scheduleAppointmentRequest){
        return modelMapper.map(scheduleAppointmentRequest, ScheduleAppointment.class);
    }
}
