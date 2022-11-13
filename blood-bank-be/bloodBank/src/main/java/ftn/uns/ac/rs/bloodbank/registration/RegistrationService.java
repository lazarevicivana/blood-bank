package ftn.uns.ac.rs.bloodbank.registration;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.applicationUser.service.ApplicationUserService;
import ftn.uns.ac.rs.bloodbank.customer.model.Customer;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder encoder;

    public String register(Customer request) throws IOException {
        var isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new ApiBadRequestException("Email is in incorrect format!");
        }
        var token = applicationUserService.signUpUser(hashPassword(request));
        mailService.sendEmail(request, token);

        return token;
    }

    public Customer hashPassword(Customer customer) {
        customer.setPassword(encoder.encode(customer.getPassword()));
        return customer;
    }

    public JwtResponse login(LoginRequest loginRequest) {
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

    @Transactional
    public String confirmToken(String token) {
        var confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        var expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        applicationUserService.enableApplicationUser(confirmationToken.getUser().getUsername());
        return "confirmed";
    }
}