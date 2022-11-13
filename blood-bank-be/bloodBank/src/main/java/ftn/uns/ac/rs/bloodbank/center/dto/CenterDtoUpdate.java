package ftn.uns.ac.rs.bloodbank.center.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDtoUpdate {
    private UUID id;
    private  String name;
    private  String description;
    private  String avgGrade;
    private  String city;
    private  String street;
    private  String country;
    private  String streetNumber;
    private  Double longitude;
    private  Double latitude;
}
