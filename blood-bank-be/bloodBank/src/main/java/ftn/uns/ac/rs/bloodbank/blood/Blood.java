package ftn.uns.ac.rs.bloodbank.blood;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

public class Blood {
    private UUID id;
    @Enumerated(EnumType.STRING)
    private BloodType  bloodType;
    private Integer bloodUnit;
}
