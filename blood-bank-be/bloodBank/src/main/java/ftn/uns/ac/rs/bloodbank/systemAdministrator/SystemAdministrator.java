package ftn.uns.ac.rs.bloodbank.systemAdministrator;


import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.sharedModel.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
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

}
