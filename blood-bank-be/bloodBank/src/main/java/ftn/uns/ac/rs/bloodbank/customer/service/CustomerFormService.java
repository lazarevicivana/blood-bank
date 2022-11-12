package ftn.uns.ac.rs.bloodbank.customer.service;

import ftn.uns.ac.rs.bloodbank.customer.model.CustomerForm;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerFormRepository;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerFormService {
    private final CustomerFormRepository customerFromRepository;

    public CustomerForm createCustomerForm(CustomerForm customerForm){
        customerFromRepository.save(customerForm);
        return customerForm;
    }
}
