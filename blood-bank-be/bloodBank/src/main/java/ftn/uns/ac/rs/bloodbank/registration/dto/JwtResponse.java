package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.applicationUser.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
    private ApplicationUser user;
}
