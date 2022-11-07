package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import ftn.uns.ac.rs.bloodbank.complains.Complain;
import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import ftn.uns.ac.rs.bloodbank.applicationUser.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(mappedBy="customer")
    private Set<Complain> complains;

    public Customer(String username, String password, String name, String surname, String phone, String jmbg, String email, Boolean locked, GenderType gender, UserRole userRole, Address address, Boolean enabled, boolean deleted, Profession profession, Integer penalty) {
        super(username, password, name, surname, phone, jmbg, email, locked, gender, userRole, address, enabled, deleted);
        this.profession = profession;
        this.penalty = penalty;
    }
}
