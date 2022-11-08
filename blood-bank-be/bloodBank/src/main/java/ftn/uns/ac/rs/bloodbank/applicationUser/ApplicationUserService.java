package ftn.uns.ac.rs.bloodbank.applicationUser;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository ;


    public List<ApplicationUser> getAllAplicationUsers(){ return applicationUserRepository.findAll(); }

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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }

}
























