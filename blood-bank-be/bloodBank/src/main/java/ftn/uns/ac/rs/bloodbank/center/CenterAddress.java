package ftn.uns.ac.rs.bloodbank.center;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@Entity
@Table
@Getter
@Setter
public class CenterAddress {
    @Id
    @GeneratedValue
    private UUID id;
    private String city;
    private String street;
    private String country;
    private String number;
    private Double longitude;
    private Double latitude;

    public CenterAddress(String city, String street, String country, String number) {
        this.city = city;
        this.street = street;
        this.country = country;
        this.number = number;
    }

    public CenterAddress() {
    }
}
