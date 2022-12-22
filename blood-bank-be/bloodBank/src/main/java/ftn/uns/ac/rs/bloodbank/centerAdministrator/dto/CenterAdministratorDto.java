package ftn.uns.ac.rs.bloodbank.centerAdministrator.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.UserRole;
import ftn.uns.ac.rs.bloodbank.registration.dto.AddressRequest;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterAdministratorDto {
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String password;
    @NonNull
    private String phone;
    @NonNull
    private String jmbg;
    @NonNull
    private String email;
    @NonNull
    private GenderType gender;
    private String city;
    private String street;
    private String country;
    private String streetNumber;
    private Boolean enabled;
    private boolean deleted;
    private UserRole role;
    private UUID center;
    private boolean firstLogIn;
}
