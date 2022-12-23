package ftn.uns.ac.rs.bloodbank.blood.controller;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodBankDto;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodBankService;
import ftn.uns.ac.rs.bloodbank.mapper.CenterMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/blood-bank")
@RequiredArgsConstructor
public class BloodBankController {
	private final BloodBankService bloodBankService;
	private final CenterMapper centerMapper;
	@GetMapping(value = "{centerId}")
	public ResponseEntity<List<BloodBankDto>>getBanksByCenterId(@PathVariable("centerId") UUID centerId){
		var banks = bloodBankService.getBanksByCenterId(centerId)
				.stream()
				.map(centerMapper::MapBloodBankToBloodBankDto)
				.toList();
		return ResponseEntity.ok(banks);
	}

}
