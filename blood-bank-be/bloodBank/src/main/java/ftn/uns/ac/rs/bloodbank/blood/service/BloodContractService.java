package ftn.uns.ac.rs.bloodbank.blood.service;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import ftn.uns.ac.rs.bloodbank.blood.dto.OfferDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodUnit;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodBankRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodUnitRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiBadRequestException;
import ftn.uns.ac.rs.bloodbank.globalExceptions.ApiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BloodContractService {
    private final BloodContractRepository bloodContractRepository;
    private final BloodUnitRepository bloodUnitRepository;
    private final BloodOfferRepository bloodOfferRepository;
    private final BloodBankRepository bloodBankRepository;
    private final CenterRepository centerRepository;
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
        //rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_STATIC, MessagingConfig.QUEUE_STATIC_H1, "Works");
    }
    public List<BloodContract> getAvailableContracts(){
        var con = this.bloodContractRepository.getAvailableContracts();
        var list = con.stream().filter(bloodContract -> bloodOfferRepository.getOfferForContract(bloodContract.getId()).isEmpty())
                .toList();
        return list;
    }

    public void createOffer(OfferDto offerDto) {
        var contract = bloodContractRepository.findById(offerDto.getContactId())
                .orElseThrow(()-> new ApiNotFoundException("Contract not found"));
        var center = centerRepository.findById(offerDto.getCenterId())
                .orElseThrow(()-> new ApiNotFoundException("Center not found"));
        var bloodBanks = bloodBankRepository.getBanksByCenterId(center.getId());
        if(bloodBanks.isEmpty()){
            throw  new ApiBadRequestException("No blood supplies for this blood bank");
        }
        if(bloodBanks.size() < contract.getBloodUnits().size()){
            throw  new ApiBadRequestException("No enough blood supplies for this blood bank");
        }
        bloodBanks.forEach((bloodBank -> contract.getBloodUnits().forEach((bloodUnit -> {
            if(bloodUnit.getBloodType() ==bloodBank.getBloodType()){
                if(bloodBank.getBloodUnit() < bloodUnit.getBloodAmount()){
                    throw new ApiBadRequestException("Not enough blood for type: " + bloodUnit.getBloodType());
                }
            }
        }))));
        var offer = BloodOffer.builder()
                .offerDate(new Date())
                .bloodContract(contract)
                        .center(center)
                                .build();
        bloodOfferRepository.save(offer);
    }
}
