package ftn.uns.ac.rs.bloodbank.taskRunners;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodContractRepository;
import ftn.uns.ac.rs.bloodbank.blood.service.BloodTransportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
public class BloodTransferTask implements CommandLineRunner {

    private final BloodContractRepository bloodContractRepository;
    private final BloodTransportService bloodTransportService;


    @Override
    public void run(String... args) throws Exception {
        Date today = getToday();
        var bloodContracts = bloodContractRepository.findAll();
        bloodContracts.stream().forEach(b -> {
            if(b.getDeliveryDate().compareTo(today) == 0){
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
