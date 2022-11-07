package ftn.uns.ac.rs.bloodbank.centerAdministrator;

import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.appointment.Appointment;
import ftn.uns.ac.rs.bloodbank.center.Center;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity(name = "CenterAdministrator")
@DiscriminatorValue("1")
public class CenterAdministrator extends ApplicationUser {
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center;
    @ManyToMany(mappedBy = "centerAdministrators")
    private Set<Appointment> appointments;

}
