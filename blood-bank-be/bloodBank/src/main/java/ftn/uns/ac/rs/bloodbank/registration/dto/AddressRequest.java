package ftn.uns.ac.rs.bloodbank.registration.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class AddressRequest {
    private String city;
    private String street;
    private String country;
    private String streetNumber;

}
