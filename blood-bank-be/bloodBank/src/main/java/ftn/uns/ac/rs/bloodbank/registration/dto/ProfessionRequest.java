package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.customer.model.ProfessionStatus;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ProfessionRequest {
    private String professionDescription;
    private ProfessionStatus professionStatus;
}
