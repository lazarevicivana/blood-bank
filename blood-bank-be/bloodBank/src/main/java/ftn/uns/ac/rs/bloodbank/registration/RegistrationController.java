package ftn.uns.ac.rs.bloodbank.registration;

import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import ftn.uns.ac.rs.bloodbank.registration.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    @PostMapping(path = "login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(registrationService.login(loginRequest));
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
