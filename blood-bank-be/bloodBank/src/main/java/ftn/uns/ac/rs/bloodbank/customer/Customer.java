package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.aplicationUser.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity(name = "Customer")
@Table(name="customer")
public class Customer extends ApplicationUser {
    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    public Customer(ApplicationUser applicationUser, Profession profession){

    }

    public Customer(Profession profession) {
        this.profession = profession;
    }

    public Customer() {

    }
}
