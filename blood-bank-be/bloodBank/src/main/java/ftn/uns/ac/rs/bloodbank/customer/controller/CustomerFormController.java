package ftn.uns.ac.rs.bloodbank.customer.controller;

import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormRequest;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormResponse;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerFormService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/customer-form")
public class CustomerFormController {
    private final MapperService mapperService;
    private final CustomerFormService customerFormService;
    @PostMapping
    public ResponseEntity<CustomerFormResponse> createCustomerForm(@RequestBody CustomerFormRequest customerFormRequest){
            var customerForm = mapperService.CustomerFormDtoToCustomerForm(customerFormRequest);
            var newCustomerForm = customerFormService.createCustomerForm(customerForm);
            var customerFormResponse = mapperService.CustomerFormToCustomerFormDto(newCustomerForm);
            return new ResponseEntity<CustomerFormResponse>(customerFormResponse, HttpStatus.CREATED);
    }
}

