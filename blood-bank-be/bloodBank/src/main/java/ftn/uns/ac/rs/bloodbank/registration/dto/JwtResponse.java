package ftn.uns.ac.rs.bloodbank.registration.dto;

import ftn.uns.ac.rs.bloodbank.applicationUser.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
    private UUID id;
    private String username;
    private String email;
    private UserRole role;
}
