package ftn.uns.ac.rs.bloodbank.center.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.UserRole;
import ftn.uns.ac.rs.bloodbank.registration.dto.AddressRequest;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterAdministratorDtoResponse {
    @NonNull
    private String id;
    @NonNull
    private String username;
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
    private GenderType gender;
    @NonNull
    private AddressRequest addressRequest;
    @NonNull
    private Boolean enabled;
    @NonNull
    private boolean deleted;
    private UserRole role;
}
