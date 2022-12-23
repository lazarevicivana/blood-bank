package ftn.uns.ac.rs.bloodbank.customer.service;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerQRCode;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerQRCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
