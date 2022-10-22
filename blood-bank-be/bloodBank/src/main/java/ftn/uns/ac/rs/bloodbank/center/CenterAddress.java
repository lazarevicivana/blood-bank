package ftn.uns.ac.rs.bloodbank.center;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table
@Getter
@Setter
public class CenterAddress {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "city",nullable = false,columnDefinition = "TEXT")
    private String city;
    @Column(name = "street",nullable = false,columnDefinition = "TEXT")
    private String street;
    @Column(name = "country",nullable = false,columnDefinition = "TEXT")
    private String country;
    @Column(name = "streetNumber",nullable = false,columnDefinition = "TEXT")
    private String streetNumber;
    private Double longitude;
    private Double latitude;

    public CenterAddress(String city, String street, String country, String number) {
        this.city = city;
        this.street = street;
        this.country = country;
        this.streetNumber = number;
    }

    public CenterAddress() {
    }
}
