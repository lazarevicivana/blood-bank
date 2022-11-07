package ftn.uns.ac.rs.bloodbank.appointment;

import ftn.uns.ac.rs.bloodbank.center.Center;
import ftn.uns.ac.rs.bloodbank.centerAdministrator.CenterAdministrator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Appointment")
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false,columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    private Center center;
    @ManyToMany
    @JoinTable(
            name = "appointment_stuff",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "stuff_id"))
    private Set<CenterAdministrator> centerAdministrators;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
}
