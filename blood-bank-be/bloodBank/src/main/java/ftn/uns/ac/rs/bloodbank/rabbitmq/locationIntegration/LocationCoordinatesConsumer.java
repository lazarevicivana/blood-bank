package ftn.uns.ac.rs.bloodbank.rabbitmq.locationIntegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContractService;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodTransportService;
import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import ftn.uns.ac.rs.bloodbank.rabbitmq.ICustomerMq;
import ftn.uns.ac.rs.bloodbank.rabbitmq.hospitalIntegration.BloodContractConsumer;
import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationCoordinatesConsumer implements ICustomerMq  {
    private static final Logger log = LoggerFactory.getLogger(BloodContractConsumer.class);
    private final ObjectMapper objectMapper;
    private final BloodTransportService bloodTransportService;

    @RabbitListener(queues="${QUEUE_LOCATION}")
    public void handler(String message) {
        log.info("Consumer> " + message);
        var map = new CoordinateDto();
        try {
            map = objectMapper.readValue(message, CoordinateDto.class);
        }
        catch (JsonProcessingException exception){
            log.error("Mapper> "+ exception.getCause());
        }
        bloodTransportService.saveCoordinates(map);
        log.info("Domain> " + map.getLatitude());
    }
}
