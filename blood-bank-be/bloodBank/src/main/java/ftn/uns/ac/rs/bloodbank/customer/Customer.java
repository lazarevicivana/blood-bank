package ftn.uns.ac.rs.bloodbank.customer;


import ftn.uns.ac.rs.bloodbank.aplicationUser.Address;
import ftn.uns.ac.rs.bloodbank.aplicationUser.ApplicationUser;
import ftn.uns.ac.rs.bloodbank.aplicationUser.GenderType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "Customer")
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID id;

    private String username;

    private String password;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    private GenderType gender;


//public Customer(String name, String surname, String username, String password, String phone, String jmbg, String email, Address address, GenderType gender, Profession profession) {
//    super(name, surname,username,password,  phone, jmbg, email, address, gender);
//    this.profession = profession;
//}

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profession", referencedColumnName = "id")
    private Profession profession;

    public Customer(String username, String password, String name, String surname, String phone, String jmbg, String email, Address address, GenderType gender, Profession profession) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.jmbg = jmbg;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.profession = profession;
    }





}
