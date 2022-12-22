package ftn.uns.ac.rs.bloodbank.applicationUser.service;
import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.applicationUser.repository.ApplicationUserRepository;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationToken;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.transaction.Transactional;
import java.util.List;


@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final ApplicationUserRepository applicationUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,username)));
    }
    public String signUpUser(ApplicationUser applicationUser){
        var userExists = applicationUserRepository.
                findByUsername(applicationUser.getUsername())
                .isPresent();
        if(userExists){
            throw new ApiBadRequestException("username already taken");
        }
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
    
    public List<ApplicationUser> getAllAplicationUsers(){
        return applicationUserRepository.findAll();
    }

    public ApplicationUser getApplicationUser(UUID id){
        return  applicationUserRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Application user with id: " + id + "does not exist"));

    }
    public ApplicationUser getByUsername(String name) {
        return applicationUserRepository.getByUsername(name)
                .orElseThrow(() -> new ApiNotFoundException("Aplication user with this usernmame doesnt exist."));

    }
    
    @Transactional
    public void updateApplicationUser(ApplicationUser applicationUser){
        var currentUser = getApplicationUser(applicationUser.getId());

        if (applicationUser.getUsername() != null) {
            var checkUsername = applicationUserRepository.getByUsername(applicationUser.getUsername());
            if(checkUsername.isPresent() &&  applicationUser.getId() != checkUsername.get().getId()){
                throw new ApiBadRequestException("This username is already taken.");
            }
            currentUser.setUsername(applicationUser.getUsername());
        }
        if(applicationUser.getName()!=null){
            currentUser.setName(applicationUser.getName());
        }
        if(applicationUser.getSurname()!=null){
            currentUser.setSurname(applicationUser.getSurname());
        }

        if(applicationUser.getJmbg()!=null){
            currentUser.setJmbg(applicationUser.getJmbg());
        }

        if(applicationUser.getEmail()!=null){
            currentUser.setEmail(applicationUser.getEmail());
        }
        if(applicationUser.getPassword()!=null){
            currentUser.setPassword(hashPassword(applicationUser.getPassword()));
        }
        if(applicationUser.getPhone()!=null){
            currentUser.setPhone(applicationUser.getPhone());
        }
        if(applicationUser.getGender()!=null){
            currentUser.setGender(applicationUser.getGender());
        }
        if(applicationUser.getAddress().getCountry()!=null) {
            currentUser.getAddress().setCountry(applicationUser.getAddress().getCountry());
        }
        if(applicationUser.getAddress().getCity()!=null) {
            currentUser.getAddress().setCity(applicationUser.getAddress().getCity());

        }
        if(applicationUser.getAddress().getStreet()!=null) {
            currentUser.getAddress().setStreet(applicationUser.getAddress().getStreet());


        }
        if(applicationUser.getAddress().getStreetNumber()!=null) {
            currentUser.getAddress().setStreetNumber(applicationUser.getAddress().getStreetNumber());
        }
        if(applicationUser.getFirstLogIn()!=null) {
            currentUser.setFirstLogIn(false);
        }

    }
    public String hashPassword(String userPassword){
        var encodedPassword = encoder.encode(userPassword);
        return encodedPassword;
    }

}
























