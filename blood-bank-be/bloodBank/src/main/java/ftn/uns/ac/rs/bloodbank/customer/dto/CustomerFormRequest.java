package ftn.uns.ac.rs.bloodbank.customer.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFormRequest {
    private boolean isAge;
    private boolean isWeight;
    private boolean hadTransfusion;
    private boolean hadCancer;
    private boolean isSick;
    private boolean isPregnant;
    private boolean onPeriod;
    private boolean isSexual;
    private boolean isAllergic;
    private boolean useMedication;
    private UUID customerId;
    private Date submissionDate;
}
