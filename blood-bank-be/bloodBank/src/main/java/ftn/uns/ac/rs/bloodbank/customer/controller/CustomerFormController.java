package ftn.uns.ac.rs.bloodbank.customer.controller;

import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormRequest;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerFormResponse;
import ftn.uns.ac.rs.bloodbank.customer.model.PatientValidDonor;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerFormService;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/customer-form")
public class CustomerFormController {
    private final MapperService mapperService;
    private final CustomerFormService customerFormService;
    @PostMapping
    public ResponseEntity<CustomerFormResponse> createCustomerForm(@RequestBody CustomerFormRequest customerFormRequest){
            var newCustomerForm = customerFormService.createCustomerForm(customerFormRequest);
            var customerFormResponse = mapperService.CustomerFormToCustomerFormDto(newCustomerForm);
            return new ResponseEntity<>(customerFormResponse, HttpStatus.CREATED);
    }
    @GetMapping(value = "/searchCenterDonors/{patientId}")
    public ResponseEntity<ArrayList<PatientValidDonor>> checkIfPatientSuitableBloodDonor(@PathVariable("patientId") UUID patientId){
        var patientSuitabilities = customerFormService.checkIfPatientSuitableBloodDonor(patientId);
        return new ResponseEntity<>(patientSuitabilities,HttpStatus.OK);
    }
}

