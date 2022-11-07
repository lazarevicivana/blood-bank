package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import ftn.uns.ac.rs.bloodbank.applicationUser.UserRole;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("3")
public class Customer extends ApplicationUser {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession", referencedColumnName = "id")
    private Profession profession;
    private Integer penalty;


}
