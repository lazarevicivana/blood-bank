package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.UserRole;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CustomerRequest {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    private AddressRequest address;
    private UserRole role;
    private ProfessionRequest profession;
    private GenderType gender;



}
