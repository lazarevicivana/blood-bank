package ftn.uns.ac.rs.bloodbank.customer.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PatientValidDonor implements Serializable {
    private String reason;
    private boolean isValidDonor;
}
