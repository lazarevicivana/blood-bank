package ftn.uns.ac.rs.bloodbank.customer.controller;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserDtoResponse;
import ftn.uns.ac.rs.bloodbank.appointment.dto.AppointmentQRCodeDto;
import ftn.uns.ac.rs.bloodbank.appointment.service.QRGeneratorService;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.customer.model.PatientValidDonor;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerQRCodeService;
import ftn.uns.ac.rs.bloodbank.customer.service.CustomerService;
import ftn.uns.ac.rs.bloodbank.customer.dto.CustomerSearchDto;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final QRGeneratorService qrGeneratorService;
    private final MapperService mapperService;

    @GetMapping(path = "/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();}

    @GetMapping(value = "/{id}" )
    public Customer getById(@Parameter(name="id", description = "ID of a greeting to return", required = true) @PathVariable("id") UUID id) {
        return customerService.getById(id);
//        return  customerService.getById(id);}

    }
    @GetMapping(value = "/getCenterDonors/{centerId}" )
    public List<ApplicationUserDtoResponse> getCenterDonors(@PathVariable("centerId") UUID centerId)
    {
        var mappedCustomers = customerService
                .getCenterDonors(centerId)
                .stream()
                .map(mapperService::CustomerToAppUserDto).toList();
        return mappedCustomers;
    }

    @PostMapping(value = "/searchCenterDonors/{centerId}")
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public List<Customer> searchCenterDonors(@PathVariable("centerId") UUID centerId, @RequestBody CustomerSearchDto dto)
    {
        return customerService.searchCenterDonors(centerId,dto);
    }

    @GetMapping(path = "/getDonors" )
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public List<Customer> getDonors()
    {
        return customerService.getDonors();
    }

    @PostMapping(path = "/searchDonors")
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public List<Customer> searchDonors(@RequestBody CustomerSearchDto dto)
    {
        return customerService.searchDonors(dto);
    }

    @GetMapping(path = "/getCustomersQrCodes/{customerId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ArrayList<AppointmentQRCodeDto> getCustomersQrCodes(@PathVariable("customerId") UUID customerId) throws ChecksumException, NotFoundException, IOException, FormatException {
        var response = qrGeneratorService.getCustomerCodes(customerId);
        System.out.println(response);
        return  response;
    }
}
