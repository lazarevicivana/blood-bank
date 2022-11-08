package ftn.uns.ac.rs.bloodbank.registration.token;

import ftn.uns.ac.rs.bloodbank.applicationUser.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConfirmationToken")
@Table(name = "confirmatin_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "customer_id"
    )
    private ApplicationUser user;
    private LocalDateTime confirmedAt;
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, ApplicationUser user){
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
