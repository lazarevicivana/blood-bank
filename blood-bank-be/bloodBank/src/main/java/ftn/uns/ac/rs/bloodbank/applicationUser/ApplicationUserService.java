package ftn.uns.ac.rs.bloodbank.applicationUser;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationToken;
import ftn.uns.ac.rs.bloodbank.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final ApplicationUserRepository applicationUserRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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
            throw new IllegalStateException("username already taken");
        }
//        var encodePassword  =bCryptPasswordEncoder
//                .encode(applicationUser.getPassword());
//        applicationUser.setPassword(encodePassword);
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
        return applicationUserRepository.GetByUsername(name)
                .orElseThrow(() -> new ApiNotFoundException("Aplication user with this usernmame doesnt exist."));

    }
    
    @Transactional
    public void updateApplicationUser(ApplicationUser applicationUser){
        var currentUser = getApplicationUser(applicationUser.getId());

        if (applicationUser.getUsername() != null) {
            var checkUsername = applicationUserRepository.GetByUsername(applicationUser.getUsername());
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
            currentUser.setPassword(applicationUser.getPassword());
        }
        if(applicationUser.getPhone()!=null){
            currentUser.setPhone(applicationUser.getPhone());
        }
        if(applicationUser.getGender()!=null){
            currentUser.setGender(applicationUser.getGender());
        }

    }

}
























