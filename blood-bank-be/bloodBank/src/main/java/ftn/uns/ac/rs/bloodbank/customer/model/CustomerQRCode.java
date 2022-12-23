package ftn.uns.ac.rs.bloodbank.customer.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "Customer_qr_code")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerQRCode {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @OneToOne
    private Customer customer;
    @Column(length = 100000)
    private String QRCode;

}
