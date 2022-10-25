package ftn.uns.ac.rs.bloodbank.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getById(UUID id){
        Optional<Customer> cus = customerRepository.findById(id);

        return  customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id:" + id + "does not exist"));
    }



}
