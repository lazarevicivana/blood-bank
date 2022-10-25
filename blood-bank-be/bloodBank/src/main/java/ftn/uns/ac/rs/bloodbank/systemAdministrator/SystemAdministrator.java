package ftn.uns.ac.rs.bloodbank.systemAdministrator;


import ftn.uns.ac.rs.bloodbank.aplicationUser.Address;
import ftn.uns.ac.rs.bloodbank.aplicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.aplicationUser.GenderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity(name = "SystemAdministrator")
@Table(name="system_administrator")
public class SystemAdministrator extends ApplicationUser {

    public SystemAdministrator() {
    }
    public SystemAdministrator(String name, String surname, String phone, String jmbg, String email, Address address, GenderType gender) {
        super(name, surname, phone, jmbg, email, address, gender);
    }
}
