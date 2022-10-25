package ftn.uns.ac.rs.bloodbank.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineCustomer(CustomerRepository customerRepository){
        return argd -> {
          Customer customer = new Customer();
        };
    }

}
