package ftn.uns.ac.rs.bloodbank.customer.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.UserRole;
import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    private UserRole userRole;
    private Address address;
}
