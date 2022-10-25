package ftn.uns.ac.rs.bloodbank.aplicationUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
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

    public ApplicationUser(String name, String surname, String phone, String jmbg, String email, Address address, GenderType gender) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.jmbg = jmbg;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }
}
