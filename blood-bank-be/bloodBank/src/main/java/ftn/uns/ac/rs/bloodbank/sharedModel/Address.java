package ftn.uns.ac.rs.bloodbank.sharedModel;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor

@Entity(name = "Address")
@Table(name="address")
public class Address implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Column(name = "city",nullable = false,columnDefinition = "TEXT")

    private String city;

    @Column(name = "street",nullable = false,columnDefinition = "TEXT")

    private String street;

    @Column(name = "country",nullable = true,columnDefinition = "TEXT")

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
