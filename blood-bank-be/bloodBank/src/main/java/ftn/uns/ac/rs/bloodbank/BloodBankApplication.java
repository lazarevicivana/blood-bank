package ftn.uns.ac.rs.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BloodBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodBankApplication.class, args);
    }

}
