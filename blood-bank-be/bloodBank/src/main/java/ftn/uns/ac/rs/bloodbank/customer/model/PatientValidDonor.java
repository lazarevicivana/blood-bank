package ftn.uns.ac.rs.bloodbank.customer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientValidDonor {
    private String reason;
    private boolean isValidDonor;
}
