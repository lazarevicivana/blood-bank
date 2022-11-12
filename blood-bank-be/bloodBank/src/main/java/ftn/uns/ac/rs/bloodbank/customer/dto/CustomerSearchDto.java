package ftn.uns.ac.rs.bloodbank.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchDto {
    private String searchName;
    private String searchSurname;
}
