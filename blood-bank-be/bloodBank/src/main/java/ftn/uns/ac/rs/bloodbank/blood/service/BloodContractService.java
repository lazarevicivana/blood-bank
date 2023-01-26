package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodUnit;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodUnitRepository;
import ftn.uns.ac.rs.bloodbank.rabbitmq.connectionFactory.MessagingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BloodContractService {
    private final BloodContractRepository bloodContractRepository;
    private final BloodUnitRepository bloodUnitRepository;
    private final RabbitTemplate rabbitTemplate;
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
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_STATIC, MessagingConfig.QUEUE_STATIC_H1, "Works");
    }
}
