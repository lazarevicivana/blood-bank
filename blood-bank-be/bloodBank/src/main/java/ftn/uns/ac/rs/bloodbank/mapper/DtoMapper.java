package ftn.uns.ac.rs.bloodbank.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapper {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
