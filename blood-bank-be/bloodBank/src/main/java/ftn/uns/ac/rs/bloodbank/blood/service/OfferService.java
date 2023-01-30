package ftn.uns.ac.rs.bloodbank.blood.service;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.rabbitmq.connectionFactory.MessagingConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class OfferService {
    private final BloodOfferRepository bloodOfferRepository;
    private final BloodContractRepository bloodContractRepository;
    private final RabbitTemplate rabbitTemplate;
    private static final int dateLap = 2;
    private static final Logger log = LoggerFactory.getLogger(OfferService.class);
    @Scheduled(cron = "59 * * * * *")
    //@Scheduled(cron = "* * * 1 * * ")
    //every day in the month
    public void sendOffer(){
        Instant now = Instant.now();
        var contracts = bloodContractRepository.getAvailableContracts();
        contracts.forEach(bloodContract -> {
            var deliveryDate = bloodContract.getDeliveryDate().toInstant();
            var duration = Duration.between(deliveryDate, now);
            if(duration.toDays() < dateLap){
                var offers = bloodOfferRepository.getOfferForContract(bloodContract.getId());
                var off = offers.stream().findFirst();
                if(off.isEmpty()){
                    var message1 = "No blood bank is able to deliver blood for hospital: "
                            + bloodContract.getHospitalName();
                    rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_STATIC
                            , MessagingConfig.QUEUE_STATIC_H1,message1);
                    log.info(message1);
                }
                else{
                    var offer = off.get();
                    var msg2 = "Blood bank: " + offer.getCenter().getName() + " will deliver blood at date: "
                            + bloodContract.getDeliveryDate() + " to hospital: "
                            + bloodContract.getHospitalName();
                    rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_STATIC
                            , MessagingConfig.QUEUE_STATIC_H1,msg2
                            );
                    offer.setSended(true);
                    bloodOfferRepository.save(offer);
                    log.info(msg2);
                }
            }
        });
    }

}
