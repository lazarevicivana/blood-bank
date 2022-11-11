package ftn.uns.ac.rs.bloodbank.center.dto;

import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDtoResponse {
    @NonNull
    private String id;
    @NonNull
    private  String name;
    private  String description;
    private  Double avgGrade;
    @NonNull
    private  String city;
    @NonNull
    private  String street;
    @NonNull
    private  String country;
    @NonNull
    private  String streetNumber;
    private  Double longitude;
    private  Double latitude;
}
