package ftn.uns.ac.rs.bloodbank.center;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class Center {
    private UUID id;
    private String name;
    private CenterAddress centerAddress;
    private String description;
    private Double avgGrade;

    public Center(UUID id, String name, CenterAddress centerAddress, String description, Double avgGrade) {
        this.id = id;
        this.name = name;
        this.centerAddress = centerAddress;
        this.description = description;
        this.avgGrade = avgGrade;
    }
}
