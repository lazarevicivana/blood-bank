package ftn.uns.ac.rs.bloodbank.blood.dto;

import ftn.uns.ac.rs.bloodbank.blood.model.BloodType;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BloodDonationDto implements Serializable {
    private  Integer bloodUnit;
    private  BloodType bloodType;
    private  String noteForDoctor;
}
