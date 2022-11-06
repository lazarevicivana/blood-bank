package ftn.uns.ac.rs.bloodbank.mapper;

import ftn.uns.ac.rs.bloodbank.center.Center;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDto;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoResponse;
import ftn.uns.ac.rs.bloodbank.center.dto.CenterDtoUpdate;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MapperService {
    private final ModelMapper modelMapper;

    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }
    public CenterDtoResponse CenterToCenterDto(Center center){
        return modelMapper.map(center,CenterDtoResponse.class);
    }
    public Center CenterDtoToCenter(CenterDto centerDto){
        return modelMapper.map(centerDto,Center.class);
    }
    public Center CenterDtoUpdateToCenter(CenterDtoUpdate centerDto){
        return modelMapper.map(centerDto,Center.class);
    }

}
