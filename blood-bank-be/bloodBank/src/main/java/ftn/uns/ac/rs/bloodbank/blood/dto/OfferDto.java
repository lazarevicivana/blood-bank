package ftn.uns.ac.rs.bloodbank.blood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private UUID centerId;
    private Long contactId;
}
