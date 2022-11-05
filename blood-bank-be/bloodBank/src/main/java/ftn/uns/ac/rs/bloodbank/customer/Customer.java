package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.sharedModel.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import ftn.uns.ac.rs.bloodbank.sharedModel.UserRole;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Customer")
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends ApplicationUser {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession", referencedColumnName = "id")
    private Profession profession;

    private GenderType gender;

    public Customer(String username, String password, String name, String surname, String phone, String jmbg, String email, UserRole userRole, Address address, Boolean enabled, boolean deleted, Profession profession, GenderType gender) {
        super(username, password, name, surname, phone, jmbg, email, userRole, address, enabled, deleted);
        this.profession = profession;
        this.gender = gender;
    }
}
