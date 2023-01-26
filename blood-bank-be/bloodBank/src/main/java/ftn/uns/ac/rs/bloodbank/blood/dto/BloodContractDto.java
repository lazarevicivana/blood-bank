package ftn.uns.ac.rs.bloodbank.blood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodContractDto {
    private List<BloodUnitDto> bloodUnits;
    private Integer price;
    private Date deliveryDate;
    private String hospitalName;
}
