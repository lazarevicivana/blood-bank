package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BloodBankService {
	private final BloodBankRepository bloodBankRepository;
	public List<BloodBank> getBanksByCenterId(UUID centerId){
		return bloodBankRepository.getBanksByCenterId(centerId);
	}
}
