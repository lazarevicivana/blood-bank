package ftn.uns.ac.rs.bloodbank.blood.controller;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequestMapping(path = "api/v1/blood-transport")
@AllArgsConstructor
public class BloodTransportController {
    private final WebClient webClient;
    final String uri = "http://localhost:5050/api/v1/blood-transport";
   /* @GetMapping
    public Mono<Boolean> startTransport(){
        var bloodTransport = BloodTransportRequest
                .builder()
                .id(UUID.fromString("34713840-ddf3-49b2-9cae-47334cb6b31d"))
                .build();
        return webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bloodTransport))
                .retrieve()
                .bodyToMono(Boolean.class);
    }*/
}
