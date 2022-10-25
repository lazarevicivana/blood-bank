package ftn.uns.ac.rs.bloodbank.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "/all")
    public List<Customer> getAllCustomers(){return customerService.getAllCustomers();}
}
