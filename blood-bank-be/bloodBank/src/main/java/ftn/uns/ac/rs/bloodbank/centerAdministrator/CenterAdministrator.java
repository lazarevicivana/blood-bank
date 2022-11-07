package ftn.uns.ac.rs.bloodbank.centerAdministrator;
import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.center.Center;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity(name = "CenterAdministrator")
@Table(name="center_administrator")
public class CenterAdministrator extends ApplicationUser {
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center;

}
