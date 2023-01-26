package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.coordinateConfig.CoordinateDtoConfig;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@EnableScheduling
public class BloodTransportService {

    private static final Logger log = LoggerFactory.getLogger(BloodOffer.class);
    private final CoordinateDtoConfig coordinateDtoConfig;
    private  CoordinateDto coordinateDto ;
    private CenterRepository centerRepository;
    private  BloodOfferRepository bloodOfferRepository;
    private  BloodContractRepository bloodContractRepository;
    private BloodBankService bloodBankService;

    public BloodTransportService(CoordinateDtoConfig coordinateDtoConfig, CoordinateDto coordinateDto, WebClient webClient, CenterRepository centerRepository, BloodOfferRepository bloodOfferRepository, BloodContractRepository bloodContractRepository, BloodBankService bloodBankService) {
        this.coordinateDtoConfig = coordinateDtoConfig;
        this.centerRepository = centerRepository;
        this.bloodOfferRepository = bloodOfferRepository;
        this.bloodContractRepository = bloodContractRepository;
        this.bloodBankService = bloodBankService;
        this.coordinateDto = this.coordinateDtoConfig.getCoordinateConfig();
    }

    public void saveCoordinates(CoordinateDto map) {
        coordinateDto = map;
    }
    public CoordinateDto getCoordinates() {
     return coordinateDto;
    }

    public BloodOffer checkForTransfer(){
        LocalDate today = LocalDate.now();

        var bloodOffers = bloodOfferRepository.findAll();
        for (var b : bloodOffers)
        {
            var contract = bloodContractRepository.findById(b.getBloodContract().getId()).orElseThrow(() -> new NotFoundException("no contract"));
            var deliveryDate = contract.getDeliveryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            var timeForTransfer =deliveryDate.isEqual(today);
            if(timeForTransfer){
                bloodBankService.updateBloodUnits(contract,b.getCenter().getId());
                log.info("Domain> " + contract.getDeliveryDate());
                return b;
            }
        }
        return new BloodOffer();

    }
    void updateBloodUnits(){

    }
}
