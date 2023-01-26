package ftn.uns.ac.rs.bloodbank.taskRunners;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodOffer;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.repository.BloodOfferRepository;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodTransportService;
import ftn.uns.ac.rs.bloodbank.rabbitmq.hospitalIntegration.BloodContractConsumer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
public class BloodTransferTask implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BloodOffer.class);
    private final BloodOfferRepository bloodOfferRepository;
    private final BloodTransportService bloodTransportService;


    @Override
    public void run(String... args) throws Exception {
        Date today = getToday();
        var bloodContracts = bloodOfferRepository.findAll();
        bloodContracts.stream().forEach(b -> {
            if(b.getOfferDate().compareTo(today) == 0){
                log.info("Domain> " + b.getOfferDate());
                bloodTransportService.startTransport();
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
}
