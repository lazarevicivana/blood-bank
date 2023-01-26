package ftn.uns.ac.rs.bloodbank.blood.controller;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodTransportService;
import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/blood-transport")
@AllArgsConstructor
public class BloodTransportController {
    private final WebClient webClient;
    private final BloodTransportService bloodTransportService;
    final String uri = "http://localhost:5050/api/v1/blood-transport";
    @GetMapping(path = "transport")
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

    @GetMapping
    public ResponseEntity<CoordinateDto> getCoordinates(){
        var coordinates = bloodTransportService.getCoordinates();
        return ResponseEntity.ok(coordinates);
    }
}
