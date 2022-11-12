package ftn.uns.ac.rs.bloodbank.customer.controller;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();}

    @GetMapping(value = "/{id}" )
    public Customer getById(@Parameter(name="id", description = "ID of a greeting to return", required = true) @PathVariable("id") UUID id) {
        return customerService.getById(id);
//        return  customerService.getById(id);}

    }
}
