package ftn.uns.ac.rs.bloodbank.blood.model;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BloodContractService {
    private final BloodContractRepository bloodContractRepository;
    private final BloodUnitRepository bloodUnitRepository;
    @Transactional
    public void createContract(BloodContractDto contractDto){
        var contract = bloodContractRepository
                .getCurrentContract(contractDto.getHospitalName());
        contract.ifPresent(bloodContract -> bloodContract.setIsExpired(true));
        var newBloodUnits = contractDto.getBloodUnits().stream().map(bloodUnitDto ->
                        new BloodUnit(bloodUnitDto.getBloodType(),bloodUnitDto.getBloodAmount()))
                .toList();
        bloodUnitRepository.saveAll(newBloodUnits);
        var newContract = BloodContract.builder()
                .deliveryDate(contractDto.getDeliveryDate())
                .price(contractDto.getPrice())
                .hospitalName(contractDto.getHospitalName())
                .isExpired(false)
                .bloodUnits(newBloodUnits)
                .build();
        bloodContractRepository.save(newContract);
    }
}
