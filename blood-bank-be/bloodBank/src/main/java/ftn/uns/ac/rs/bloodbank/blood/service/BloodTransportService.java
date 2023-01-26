package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.coordinateConfig.CoordinateDtoConfig;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
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

import java.util.Calendar;
import java.util.Date;

@Service
@EnableScheduling
public class BloodTransportService {

    private static final Logger log = LoggerFactory.getLogger(BloodOffer.class);
    private final CoordinateDtoConfig coordinateDtoConfig;
    private  CoordinateDto coordinateDto ;
    private final WebClient webClient;
    final String uri = "http://localhost:5050/api/v1/blood-transport";
    private CenterRepository centerRepository;
    private  BloodOfferRepository bloodOfferRepository;
    private  BloodContractRepository bloodContractRepository;

    public BloodTransportService(CoordinateDtoConfig coordinateDtoConfig, CoordinateDto coordinateDto, WebClient webClient, CenterRepository centerRepository, BloodOfferRepository bloodOfferRepository, BloodContractRepository bloodContractRepository) {
        this.coordinateDtoConfig = coordinateDtoConfig;
        this.centerRepository = centerRepository;
        this.bloodOfferRepository = bloodOfferRepository;
        this.bloodContractRepository = bloodContractRepository;
        this.coordinateDto = this.coordinateDtoConfig.getCoordinateConfig();
        this.webClient = webClient;
    }

    public void saveCoordinates(CoordinateDto map) {
        coordinateDto = map;
    }
    public CoordinateDto getCoordinates() {
     return coordinateDto;
    }
    @Scheduled(fixedRate = 10000)
    public void checkForTransfer(){
        Date today = getToday();
        var bloodContracts = bloodOfferRepository.findAll();
        bloodContracts.stream().forEach(b -> {
            var contract = bloodContractRepository.findById(b.getBloodContract().getId()).orElseThrow(() -> new NotFoundException("no contract"));
            if(contract.getDeliveryDate().compareTo(today) == 0){
                log.info("Domain> " + contract.getDeliveryDate());
               startTransport(b);
            }
        });
    }
    private static Date getToday() {
        var calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        return today;
    }
    public Mono<Boolean> startTransport(BloodOffer offer){
        var center = centerRepository.findById(offer.getCenter().getId()).orElseThrow(() -> new NotFoundException("Not found center"));

        var start = LocationCoordinate
                .builder()
                .longitude(center.getCenterAddress().getLongitude())
                .latitude(center.getCenterAddress().getLongitude())
                .build();
        var finish = LocationCoordinate
                .builder()
                .longitude(48.255642)
                .latitude(28.804787)
                .build();
        var bloodTransport = BloodTransportRequest
                .builder()
                .bloodId("34713840-ddf3-49b2-9cae-47334cb6b31d")
                .start(start)
                .finish(finish)
                .quantity(2)
                .build();
        return webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bloodTransport))
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
