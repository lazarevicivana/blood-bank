package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.coordinateConfig.CoordinateDtoConfig;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
public class BloodTransportService {


    private final CoordinateDtoConfig coordinateDtoConfig;
    private  CoordinateDto coordinateDto ;
    private final WebClient webClient;
    final String uri = "http://localhost:5050/api/v1/blood-transport";

    public BloodTransportService(CoordinateDtoConfig coordinateDtoConfig, CoordinateDto coordinateDto, WebClient webClient) {
        this.coordinateDtoConfig = coordinateDtoConfig;
        this.coordinateDto = this.coordinateDtoConfig.getCoordinateConfig();
        this.webClient = webClient;
    }

    public void saveCoordinates(CoordinateDto map) {
        coordinateDto = map;
    }
    public CoordinateDto getCoordinates() {
     return coordinateDto;
    }
    public Mono<Boolean> startTransport(){
        var start = LocationCoordinate
                .builder()
                .longitude(45.255642)
                .latitude(19.804787)
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
