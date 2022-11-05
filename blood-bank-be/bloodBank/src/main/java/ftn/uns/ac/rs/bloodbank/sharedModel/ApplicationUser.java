package ftn.uns.ac.rs.bloodbank.sharedModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
public class ApplicationUser {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String jmbg;
    private String email;
    @Column(name = "role", nullable = false)
    private UserRole userRole;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;
    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "deleted")
    private boolean deleted = false;

    public ApplicationUser(String username, String password, String name, String surname, String phone, String jmbg, String email, UserRole userRole, Address address, Boolean enabled, boolean deleted) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.jmbg = jmbg;
        this.email = email;
        this.userRole = userRole;
        this.address = address;
        this.enabled = enabled;
        this.deleted = deleted;
    }
}
