package ftn.uns.ac.rs.bloodbank.center.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "CenterAddress")
@Table(name = "center_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "street_number",nullable = false,columnDefinition = "TEXT")
    private String streetNumber;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;

    public CenterAddress(String city, String street, String country, String number) {
        this.city = city;
        this.street = street;
        this.country = country;
        this.streetNumber = number;
    }

    public CenterAddress(String city, String street, String country, String streetNumber, Double longitude, Double latitude) {
        this.city = city;
        this.street = street;
        this.country = country;
        this.streetNumber = streetNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
