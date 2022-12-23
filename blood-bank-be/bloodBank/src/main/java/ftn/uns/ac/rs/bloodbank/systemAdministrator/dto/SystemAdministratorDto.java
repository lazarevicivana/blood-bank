package ftn.uns.ac.rs.bloodbank.systemAdministrator.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.UserRole;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemAdministratorDto {
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
    private boolean firstLogIn;
}
