package ftn.uns.ac.rs.bloodbank.center;

import ftn.uns.ac.rs.bloodbank.center.model.Center;
import ftn.uns.ac.rs.bloodbank.center.model.CenterAddress;
import ftn.uns.ac.rs.bloodbank.center.repository.CenterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CenterConfig {
    //@Bean
    CommandLineRunner commandLineRunner(CenterRepository centerRepository){
        return args -> {
            Center center = new Center("Vajo",new CenterAddress("Novi Sad",
                    "Vojvode Bojovica",
                    "Serbia",
                    "8a"),
                    "cc",
                    0.0);
            centerRepository.save(center);
        };
    }
}
