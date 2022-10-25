package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.aplicationUser.Address;
import ftn.uns.ac.rs.bloodbank.aplicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.aplicationUser.GenderType;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Customer")
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends ApplicationUser {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession", referencedColumnName = "id")
    private Profession profession;

    public Customer(String name, String surname, String phone, String jmbg, String email, Address address, GenderType gender, Profession profession) {
        super(name, surname, phone, jmbg, email, address, gender);
        this.profession = profession;
    }
}
