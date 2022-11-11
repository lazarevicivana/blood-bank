package ftn.uns.ac.rs.bloodbank.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffResponse {
    @NonNull
    private UUID id;

    @NonNull
    private String username;

    @NonNull
    private String name;

    @NonNull
    private String surname;
    @NonNull
    private String email;
}
