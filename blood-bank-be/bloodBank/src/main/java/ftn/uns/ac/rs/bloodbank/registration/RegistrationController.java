package ftn.uns.ac.rs.bloodbank.registration;

import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import ftn.uns.ac.rs.bloodbank.registration.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final MapperService mapperService;

    @PostMapping
    public ResponseEntity<Customer> register(@RequestBody CustomerRequest request) throws IOException {
        var address = mapperService.AdressRequestToAdress(request.getAddress());
        var profession = mapperService.ProfessionRequestToProfession(request.getProfession());
        var customer = mapperService.CustomerRequestToCustomer(request);
        customer.setAddress(address);
        customer.setProfession(profession);
        registrationService.register(customer);
     return  new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
   @PostMapping(path = "login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(registrationService.login(loginRequest));
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
