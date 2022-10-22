package ftn.uns.ac.rs.bloodbank.aplicationUser;


import ftn.uns.ac.rs.bloodbank.customer.Profession;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class ApplicationUser {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private GenderType gender;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
