package ftn.uns.ac.rs.bloodbank.customer;

import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

//    @Bean
//    CommandLineRunner commandLineCustomer(CustomerRepository customerRepository){
//        return args -> {
//          Customer customer = new Customer("Marinko","Magla","marinkocar","marinkocar","069021021","420420420","MarinkoMagla@gmail.com",
//                  new Address("Kamengrad","Sudjera Boba","Dunavija","42"),
//                  GenderType.MALE,new Profession(ProfessionStatus.EMPLOYED,"Sportista"));
//          customerRepository.save(customer);
//
//            Customer customer1 = new Customer("Borko","Bore","Borko","Bore","069021021","420420420","BorkoBore@gmail.com",
//                    new Address("Kamengrad","Sudjera Boba","Dunavija","42"),
//                    GenderType.MALE,new Profession(ProfessionStatus.EMPLOYED,"Sportista"));
//            customerRepository.save(customer1);
//        };
//    }

}
