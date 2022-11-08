package ftn.uns.ac.rs.bloodbank.applicationUser;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationToken;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,username)));
    }
    public String signUpUser(ApplicationUser applicationUser){
        var userExists = applicationUserRepository.findByUsername(applicationUser.getUsername())
                .isPresent();
        if(userExists){
            throw new IllegalStateException("username already taken");
        }
        var encodePassword  =bCryptPasswordEncoder
                .encode(applicationUser.getPassword());
        applicationUser.setPassword(encodePassword);
        applicationUserRepository.save(applicationUser);
       return sendConfirmationToken(applicationUser);
    }

    private  String sendConfirmationToken(ApplicationUser applicationUser) {
        var token  = UUID.randomUUID().toString();
        var confimationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                applicationUser

        );
        confirmationTokenService.saveConfirmationToken(confimationToken);
        //TODO send email
        return token;

    }
    public int enableApplicationUser(String username) {
        return applicationUserRepository.enableAppUser(username);
    }
}
