package ftn.uns.ac.rs.bloodbank.blood.dto;

import ftn.uns.ac.rs.bloodbank.sharedModel.LocationCoordinate;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BloodTransportRequest {
    private String bloodId;
    private Integer quantity;
    private LocationCoordinate start;
    private LocationCoordinate finish;
}
