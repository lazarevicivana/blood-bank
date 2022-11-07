package ftn.uns.ac.rs.bloodbank.systemAdministrator;


import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity(name = "SystemAdministrator")
@DiscriminatorValue("2")
public class SystemAdministrator extends ApplicationUser {

    public SystemAdministrator() {
    }

}
