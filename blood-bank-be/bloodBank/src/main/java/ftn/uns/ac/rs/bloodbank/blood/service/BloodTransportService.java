package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.coordinateConfig.CoordinateDtoConfig;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
@EnableScheduling
public class BloodTransportService {

    private static final Logger log = LoggerFactory.getLogger(BloodOffer.class);
    private final CoordinateDtoConfig coordinateDtoConfig;
    private  CoordinateDto coordinateDto ;
    private CenterRepository centerRepository;
    private  BloodOfferRepository bloodOfferRepository;
    private  BloodContractRepository bloodContractRepository;

    public BloodTransportService(CoordinateDtoConfig coordinateDtoConfig, CoordinateDto coordinateDto, WebClient webClient, CenterRepository centerRepository, BloodOfferRepository bloodOfferRepository, BloodContractRepository bloodContractRepository) {
        this.coordinateDtoConfig = coordinateDtoConfig;
        this.centerRepository = centerRepository;
        this.bloodOfferRepository = bloodOfferRepository;
        this.bloodContractRepository = bloodContractRepository;
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
            if(timeForTransfer ){
                log.info("Domain> " + contract.getDeliveryDate());
                return b;
            }
        }
        return new BloodOffer();

    }
}
