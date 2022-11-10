package ftn.uns.ac.rs.bloodbank.systemAdministrator;


import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity(name = "SystemAdministrator")
@DiscriminatorValue("2")
public class SystemAdministrator extends ApplicationUser {

    public SystemAdministrator() {
    }

}
