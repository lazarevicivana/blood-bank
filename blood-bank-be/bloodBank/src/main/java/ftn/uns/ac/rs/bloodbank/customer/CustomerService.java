package ftn.uns.ac.rs.bloodbank.customer;

import ftn.uns.ac.rs.bloodbank.appointment.model.ScheduleAppointment;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerSearchDto;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiConflictException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ScheduleAppointmentRepository scheduleAppointmentRepository;
    private final CenterRepository centerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getById(UUID id){
        Optional<Customer> cus = customerRepository.findById(id);

        return  customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id:" + id + "does not exist"));
    }

    public List<Customer> getCenterDonors(UUID centerID)
    {
        var centerExist = centerRepository.findById(centerID);
        if(centerExist.isPresent()) {
            LocalDateTime now = LocalDateTime.now();
            return scheduleAppointmentRepository.GetCenterDonors(centerID, now);
        }
        throw  new ApiNotFoundException("This center doesn't exist");
    }

    public List<Customer> searchCenterDonors(UUID centerId,CustomerSearchDto dto)
    {
        var centerExist = centerRepository.findById(centerId);
        if(centerExist.isPresent()) {
            LocalDateTime now = LocalDateTime.now();
            return scheduleAppointmentRepository.SearchCenterDonors(centerId, now, dto.getSearchName().toLowerCase(), dto.getSearchSurname().toLowerCase());
        }
        throw  new ApiNotFoundException("This center doesn't exist");
    }

    public List<Customer> getDonors() {
        LocalDateTime now = LocalDateTime.now();
        return scheduleAppointmentRepository.GetDonors(now);
    }

    public List<Customer> searchDonors(CustomerSearchDto dto) {
        LocalDateTime now = LocalDateTime.now();
        return scheduleAppointmentRepository.SearchDonors(now, dto.getSearchName().toLowerCase(), dto.getSearchSurname().toLowerCase());
    }
}
