package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.customer.ProfessionStatus;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ProfessionRequest {
    private String profession;
    private ProfessionStatus professionStatus;
}
