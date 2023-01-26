package ftn.uns.ac.rs.bloodbank.blood.dto;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodUnitDto {
    private BloodType bloodType;
    private Integer bloodAmount;
}
