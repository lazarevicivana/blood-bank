package ftn.uns.ac.rs.bloodbank.loyaltyProgram.controller;


import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserUpdate;
import ftn.uns.ac.rs.bloodbank.loyaltyProgram.model.LoyaltyProgram;
import ftn.uns.ac.rs.bloodbank.loyaltyProgram.service.LoyaltyProgramService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/loyaltyProgram")
public class LoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService){
        this.loyaltyProgramService = loyaltyProgramService;

    }

    @GetMapping("customers/{id}")
    public ResponseEntity<LoyaltyProgram> getLoyaltyProgramByCustomerID(@NonNull @PathVariable("id") UUID id){
        var program = loyaltyProgramService.getByCustomerId(id);
        return ResponseEntity.ok(program);
    }
}
