package ftn.uns.ac.rs.bloodbank.mapper;

import ftn.uns.ac.rs.bloodbank.applicationUser.dto.ApplicationUserDtoResponse;
import ftn.uns.ac.rs.bloodbank.blood.dto.BloodBankDto;
import ftn.uns.ac.rs.bloodbank.blood.model.BloodBank;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CenterMapper {
	private final ModelMapper modelMapper;

	public CenterMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.LOOSE);
	}
	public BloodBankDto MapBloodBankToBloodBankDto(BloodBank bloodBank){
		return modelMapper.map(bloodBank, BloodBankDto.class);
	}
}
