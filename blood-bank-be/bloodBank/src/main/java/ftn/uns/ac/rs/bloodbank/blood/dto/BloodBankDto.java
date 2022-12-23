package ftn.uns.ac.rs.bloodbank.blood.dto;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankDto {
	private BloodType bloodType;
	private Integer bloodUnit;
}
