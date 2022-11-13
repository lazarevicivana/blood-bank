package ftn.uns.ac.rs.bloodbank.loyaltyProgram.service;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import ftn.uns.ac.rs.bloodbank.loyaltyProgram.model.LoyaltyProgram;
import ftn.uns.ac.rs.bloodbank.loyaltyProgram.repository.LoyaltyProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LoyaltyProgramService {

    private  final LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgram getApplicationUser(UUID id){
        return  loyaltyProgramRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Loyalty program with " + id + "does not exist"));

    }

    public LoyaltyProgram getByCustomerId(UUID id){
        return loyaltyProgramRepository.getByCustomerId(id)
                .orElseThrow(() -> new ApiNotFoundException("Loyalty program with this customer doesnt exist."));

    }
}
