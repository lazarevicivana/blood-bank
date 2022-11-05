package ftn.uns.ac.rs.bloodbank.sharedModel;

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
    @Column(name = "street_number",nullable = false,columnDefinition = "TEXT")
    private String streetNumber;

    public Address(String city, String street, String country, String number) {
        this.city = city;
        this.street = street;
        this.country = country;
        this.streetNumber = number;
    }

    public Address() {

    }
}
