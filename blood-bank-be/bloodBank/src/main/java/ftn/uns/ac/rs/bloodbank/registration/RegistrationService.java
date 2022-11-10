package ftn.uns.ac.rs.bloodbank.registration;

import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUserService;
import ftn.uns.ac.rs.bloodbank.customer.Customer;
import ftn.uns.ac.rs.bloodbank.mapper.MapperService;
import ftn.uns.ac.rs.bloodbank.registration.dto.CustomerRequest;
import ftn.uns.ac.rs.bloodbank.registration.dto.JwtResponse;
import ftn.uns.ac.rs.bloodbank.registration.dto.LoginRequest;
import ftn.uns.ac.rs.bloodbank.registration.email.EmailService;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationTokenService;
import ftn.uns.ac.rs.bloodbank.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.lang.String;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final ApplicationUserService applicationUserService;
    private EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService mailService;
    private final MapperService mapperService;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;


    public String register(CustomerRequest request) throws IOException {
       var isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("email not valid");
       }
        var token = applicationUserService.signUpUser(createNewCustomer(request));
        mailService.sendEmail(request,token);

        return token;
    }
    public JwtResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        ApplicationUser userDetails = (ApplicationUser) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getUserRole());
    }

    private  Customer createNewCustomer(CustomerRequest request) {
        var address = mapperService.AdressRequestToAdress(request.getAddress());
        var profession = mapperService.ProfessionRequestToProfession(request.getProfession());
        var customer = mapperService.CustomerRequestToCustomer(request);
        customer.setAddress(address);
        customer.setProfession(profession);
        return customer;
    }

    @Transactional
    public String confirmToken(String token){
        var confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }
        var expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        applicationUserService.enableApplicationUser(confirmationToken.getUser().getUsername());
        return "confirmed";
    }

}

