package ftn.uns.ac.rs.bloodbank.blood.controller;

import ftn.uns.ac.rs.bloodbank.blood.dto.OfferDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/blood-contracts")
@RequiredArgsConstructor
public class BloodContractController {
    private final BloodContractService bloodContractService;
    @GetMapping()
    ResponseEntity<List<BloodContract>> getAvailableOffers(){
        return  ResponseEntity.ok(bloodContractService.getAvailableContracts());
    }
    @PostMapping()
    public ResponseEntity<?> CreateOffer(@RequestBody OfferDto offerDto){
        bloodContractService.createOffer(offerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
