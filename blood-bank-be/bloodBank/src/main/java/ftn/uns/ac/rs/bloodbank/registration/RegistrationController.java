package ftn.uns.ac.rs.bloodbank.registration;

import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody CustomerRequest request) throws IOException {
     return registrationService.register(request);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
