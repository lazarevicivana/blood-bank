package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.applicationUser.model.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.center.model.Center;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "CenterAdministrator")
@DiscriminatorValue("1")
public class CenterAdministrator extends ApplicationUser {
    @ManyToOne()
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center;
//    @ManyToMany(mappedBy = "centerAdministrators",cascade=CascadeType.ALL)
//    private Set<Appointment> appointments;
}
