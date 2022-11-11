package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.UserRole;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.*;
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class CustomerRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private String jmbg;
    @NonNull
    private String email;
    @NonNull
    private AddressRequest address;
    @NonNull
    private UserRole role;
    @NonNull
    private ProfessionRequest profession;
    private GenderType gender;



}
