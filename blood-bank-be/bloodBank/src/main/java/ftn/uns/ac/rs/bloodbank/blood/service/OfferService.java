package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.rabbitmq.hospitalIntegration.BloodContractConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferService {
    private final BloodOfferRepository bloodOfferRepository;
    private final BloodContractRepository bloodContractRepository;
    private final RabbitTemplate rabbitTemplate;
    private static final Logger log = LoggerFactory.getLogger(OfferService.class);
    @Scheduled(cron = "59 * * * * *")
    //@Scheduled(cron = "* * * 1 * * ")
    //every day in the month
    public void sendOffer(){
        var offers = bloodContractRepository.getAvailableOffers();
        log.info("Works");
    }

}
