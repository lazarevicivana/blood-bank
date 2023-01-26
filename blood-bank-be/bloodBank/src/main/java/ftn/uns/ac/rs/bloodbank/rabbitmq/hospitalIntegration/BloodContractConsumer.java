package ftn.uns.ac.rs.bloodbank.rabbitmq.hospitalIntegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodContractDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContract;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodContractService;
import ftn.uns.ac.rs.bloodbank.rabbitmq.ICustomerMq;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BloodContractConsumer implements ICustomerMq {
    private static final Logger log = LoggerFactory.getLogger(BloodContractConsumer.class);
    private final ObjectMapper objectMapper;
    private final BloodContractService bloodContractService;
    @RabbitListener(queues="${QUEUE_HOSPITAL}")
    public void handler(String message) {
        log.info("Consumer> " + message);
        BloodContractDto map = new BloodContractDto();
        try {
            map = objectMapper.readValue(message, BloodContractDto.class);
        }
        catch (JsonProcessingException exception){
            log.error("Mapper> "+ exception.getCause());
        }
        bloodContractService.createContract(map);
        log.info("Domain> " + map.getHospitalName());
    }
}
