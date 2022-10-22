package ftn.uns.ac.rs.bloodbank.centerAdministrator;
import ftn.uns.ac.rs.bloodbank.aplicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.center.Center;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;
@Setter
@Getter
@Entity(name = "CenterAdministrator")
@Table(name="center_administrator")
public class CenterAdministrator extends ApplicationUser {
    //treba rzmotriti!!
    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

}
