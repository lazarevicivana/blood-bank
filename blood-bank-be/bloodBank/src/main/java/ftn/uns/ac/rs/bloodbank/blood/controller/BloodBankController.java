package ftn.uns.ac.rs.bloodbank.blood.controller;

import ftn.uns.ac.rs.bloodbank.blood.dto.BloodBankDto;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodTransportRequest;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodBankService;
import ftn.uns.ac.rs.bloodbank.mapper.CenterMapper;
import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/blood-bank")
@RequiredArgsConstructor
public class BloodBankController {
	private final BloodBankService bloodBankService;
	private final CenterMapper centerMapper;
	@GetMapping(value = "{centerId}")
	public ResponseEntity<List<BloodBankDto>>getBanksByCenterId(@PathVariable("centerId") UUID centerId){
		var banks = bloodBankService.getBanksByCenterId(centerId)
				.stream()
				.map(centerMapper::MapBloodBankToBloodBankDto)
				.toList();
		return ResponseEntity.ok(banks);
	}
	private final WebClient webClient;
	final String uri = "http://localhost:5050/api/v1/blood-transport";
	@GetMapping(path = "transport")
	public Mono<Boolean> startTransport(){
		var start = LocationCoordinate
				.builder()
				.longitude(1.0)
				.latitude(1.0)
				.build();
		var finish = LocationCoordinate
				.builder()
				.longitude(10.0)
				.latitude(10.0)
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
