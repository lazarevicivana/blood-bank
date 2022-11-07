package ftn.uns.ac.rs.bloodbank.applicationUser;

import ftn.uns.ac.rs.bloodbank.sharedModel.Address;
import ftn.uns.ac.rs.bloodbank.sharedModel.GenderType;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Getter
@Setter
@DiscriminatorColumn(name="user_role", discriminatorType = DiscriminatorType.INTEGER)
public class ApplicationUser{
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
    private Boolean locked;
    private GenderType gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole userRole;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;
    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "deleted")
    private boolean deleted = false;

    public ApplicationUser(String username, String password, String name, String surname, String phone, String jmbg, String email, UserRole userRole, Address address, Boolean enabled, boolean deleted,boolean locked) {
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
        this.locked = locked;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority =
//                new SimpleGrantedAuthority(userRole.name());
//        return Collections.singleton(authority);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//
//        return !locked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//
//        return true;
//    }

//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
}
