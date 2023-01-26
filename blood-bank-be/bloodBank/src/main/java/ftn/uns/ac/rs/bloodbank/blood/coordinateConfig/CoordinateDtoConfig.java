package ftn.uns.ac.rs.bloodbank.blood.coordinateConfig;

import ftn.uns.ac.rs.bloodbank.customer.dto.CoordinateDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoordinateDtoConfig {
    @Bean
    public CoordinateDto getCoordinateConfig(){
        return new CoordinateDto();
    }
}
