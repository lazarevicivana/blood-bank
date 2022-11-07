package ftn.uns.ac.rs.bloodbank.customer;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();}

    @GetMapping(value = "/{id}" )
    public Customer getById(@Parameter(name="id", description = "ID of a greeting to return", required = true) @PathVariable("id") UUID id) {
        Customer cus = customerService.getById(id);
        return cus;
//        return  customerService.getById(id);}

    }
}
