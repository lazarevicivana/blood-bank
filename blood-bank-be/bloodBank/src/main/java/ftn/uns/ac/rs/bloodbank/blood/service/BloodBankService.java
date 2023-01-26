package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodBankRepository;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BloodBankService {
	private final BloodBankRepository bloodBankRepository;
	private final CenterRepository centerRepository;
	public List<BloodBank> getBanksByCenterId(UUID centerId){
		return bloodBankRepository.getBanksByCenterId(centerId);
	}
	void updateBloodUnits(BloodContract bloodContract, UUID centerId){
			bloodContract.getBloodUnits().forEach(bloodUnit -> {
				var banks = bloodBankRepository.getBanksByCenterId(centerId);
				banks.forEach(bloodBank -> {
					if(bloodUnit.getBloodType() == bloodBank.getBloodType()){
						var blood = bloodBank.getBloodUnit() - bloodUnit.getBloodAmount();
						bloodBank.setBloodUnit(blood);
						bloodBankRepository.save(bloodBank);
					}
				});
			});
	}
}
