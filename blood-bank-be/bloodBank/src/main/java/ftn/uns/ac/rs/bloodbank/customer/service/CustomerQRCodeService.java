package ftn.uns.ac.rs.bloodbank.customer.service;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentQRCodeDto;
import ftn.uns.ac.rs.bloodbank.appointment.repository.ScheduleAppointmentRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;

import ftn.uns.ac.rs.bloodbank.appointment.service.QRGeneratorService;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerQRCode;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerQRCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomerQRCodeService {

    private final CustomerQRCodeRepository customerQRCodeRepository;

    @Transactional
    public void createCustomerQRCode(String base64, Customer customer) {
        var customerQRCode = CustomerQRCode
                .builder()
                .customer(customer)
                .QRCode(base64)
                .build();
        customerQRCodeRepository.save(customerQRCode);
    }

    public List<CustomerQRCode> getCustomerCodes(UUID customerId) {
        return customerQRCodeRepository.getByCustomer(customerId);

    }

}
