package ftn.uns.ac.rs.bloodbank.aplicationUser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "Address")
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    private String city;
    private String street;
    private String country;
    private String number;
}
