package ftn.uns.ac.rs.bloodbank.customer;

import ftn.uns.ac.rs.bloodbank.aplicationUser.Address;
import ftn.uns.ac.rs.bloodbank.aplicationUser.GenderType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunnerCustomer(CustomerRepository customerRepository){
        return argd -> {
          Customer customer = new Customer("Marinko","Magla","marinkocar","marinkocar","069021021","420420420","MarinkoMagla@gmail.com",
                  new Address("Kamengrad","Sudjera Boba","Dunavija","42"),
                  GenderType.MALE,new Profession(ProfessionStatus.EMPLOYED,"Sportista"));
          customerRepository.save(customer);

            Customer customer1 = new Customer("Borko","Bore","Borko","Bore","069021021","420420420","BorkoBore@gmail.com",
                    new Address("Kamengrad","Sudjera Boba","Dunavija","42"),
                    GenderType.MALE,new Profession(ProfessionStatus.EMPLOYED,"Sportista"));
            customerRepository.save(customer1);
        };
    }

}
