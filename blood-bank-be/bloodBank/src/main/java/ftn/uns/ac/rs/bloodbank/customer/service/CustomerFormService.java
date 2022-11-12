package ftn.uns.ac.rs.bloodbank.customer.service;

import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormRequest;
import ftn.uns.ac.rs.bloodbank.customer.model.CustomerForm;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerFormRepository;
import ftn.uns.ac.rs.bloodbank.customer.repository.CustomerRepository;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

@Service
@AllArgsConstructor
public class CustomerFormService {
    private final CustomerFormRepository customerFromRepository;
    private final CustomerRepository customerRepository;
    private final MapperService mapperService;
    @Transient
    public CustomerForm createCustomerForm(CustomerFormRequest customerForm){
        var customer = customerRepository.findById(customerForm.getCustomerId()).orElseThrow(() ->
                new ApiBadRequestException("Customer doesnt exists"));
        var form = mapperService.CustomerFormDtoToCustomerForm(customerForm);
        form.setCustomer(customer);
        customerFromRepository.save(form);
        return form;
    }
}
