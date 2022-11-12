package ftn.uns.ac.rs.bloodbank.customer.controller;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerService;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerSearchDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @GetMapping(value = "/getCenterDonors/{centerId}" )
    public List<Customer> getCenterDonors(@PathVariable("centerId") UUID centerId)
    {
        return customerService.getCenterDonors(centerId);
    }

    @PostMapping(value = "/searchCenterDonors/{centerId}")
    public List<Customer> searchCenterDonors(@PathVariable("centerId") UUID centerId, @RequestBody CustomerSearchDto dto)
    {
        return customerService.searchCenterDonors(centerId,dto);
    }

    @GetMapping(path = "/getDonors" )
    public List<Customer> getDonors()
    {
        return customerService.getDonors();
    }

    @PostMapping(path = "/searchDonors")
    public List<Customer> searchDonors(@RequestBody CustomerSearchDto dto)
    {
        return customerService.searchDonors(dto);
    }
}
